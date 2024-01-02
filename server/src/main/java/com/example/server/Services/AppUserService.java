package com.example.server.Services;

import com.example.server.DTO.AppUserDto;
import com.example.server.Exceptions.EmailAlreadyExists;
import com.example.server.Models.*;
import com.example.server.Repositories.*;
import com.example.server.login.registration.token.ConfirmationToken;
import com.example.server.login.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    @Autowired
    private final AppUserRepository appUserRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();
        if (userExists){
            // TODO check if attributes are the same and
            // TODO If email not confirmed, send confirmation email again
//            return "Email already taken";
            throw new EmailAlreadyExists("Email Already Exists");
        }

        String encodedPassword = passwordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
//        Generate confirmation Token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
//        Save confirmation to DB
        confirmationTokenService.saveConfirmationToken(confirmationToken);

//TODO: Send Email

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public List<AppUser> getAllAccounts() {
        return appUserRepository.findAll();
    }

    public Optional<AppUser> updateFinAcctId(Long id, String email){
        appUserRepository.updateFinAcctId(id, email);
        return appUserRepository.findByEmail(email);
    }

    public AppUser updateAppUser(String old_email, String new_email, String firstName, String lastName, String phoneNumber){
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findByEmail(old_email);
        if(financialAccount.isPresent()) {
            List<AppUser> appUsers = financialAccount.get().getAppUsers();
            for(int i = 0; i < appUsers.size(); i++){
                if(appUsers.get(i).getEmail().equals(old_email)){
                    appUsers.remove(i);
                }
            }
            appUserRepository.updateAppUser(firstName, lastName, phoneNumber, new_email, old_email);
            Optional<AppUser> appUser = appUserRepository.findByEmail(new_email);

            if (appUser.isPresent()) {
                appUsers.add(appUser.get());
                financialAccount.get().setAppUsers(appUsers);
                financialAccount.get().setEmail(new_email);
                financialAccountRepository.save(financialAccount.get());
                return appUser.get();
            }
        }
        return null;
    }
    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public AppUser updateUserRole(String role, String email){

        appUserRepository.updateUserRole(role, email);
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(appUser.isPresent()){
            return appUser.get();
        }
        return null;
    }

    public void deleteUser(String email) {
        appUserRepository.deleteUser(email);
    }

    public AppUserDto getAppUserDetailsByEmail(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();

            AppUserDto userDetailsDTO = new AppUserDto();
            FinancialAccount financialAccount1 = appUser.getFinancialAccount();

            if(financialAccount1 == null){
                // if no financial account associated with app user set all other dto fields to null and return dto
                userDetailsDTO.setAppUser(appUser);
                userDetailsDTO.setFinancialAccount(null);
                userDetailsDTO.setProperties(null);
                userDetailsDTO.setLedgers(null);
                userDetailsDTO.setTransactions(null);
                return userDetailsDTO;
            }

            List<Ledger> ledgers = financialAccount1.getLedgers();
            List<TransactionTests> transactionTests = new ArrayList<>();
            List<Property> properties = new ArrayList<>();

            if(ledgers.isEmpty()){
                // if ledger is null there is no property tied to the account
                userDetailsDTO.setProperties(null);
            }else {
                // get all transactions if there are ledgers
                for(int i = 0; i < ledgers.size(); i++){
                    TransactionTests transaction = ledgers.get(i).getTransactionTests();
                    if(transaction!= null){
                        transactionTests.add(transaction);
                    }

                }
                properties.add(ledgers.get(0).getProperty());
                userDetailsDTO.setProperties(properties);
            }
            userDetailsDTO.setAppUser(appUser);
            userDetailsDTO.setFinancialAccount(financialAccount1);
            userDetailsDTO.setLedgers(ledgers);
            userDetailsDTO.setTransactions(transactionTests);

            // Set other fields in the DTO

            return userDetailsDTO;
        }
        return null; // Or throw an exception if the user is not found
    }


}
