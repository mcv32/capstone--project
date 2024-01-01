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
        appUserRepository.updateAppUser(firstName, lastName, phoneNumber, new_email, old_email);
        Optional<AppUser> appUser = appUserRepository.findByEmail(new_email);
        if(appUser.isPresent()){
            return appUser.get();
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
//            FinancialAccount financialAccount = appUser.getFinancialAccount();
//            List<Ledger> ledgers = financialAccount != null ? financialAccount.getLedgers() : Collections.emptyList();
//            List<TransactionTests> transactionTests = new ArrayList<>();
//            List<Property> properties = new ArrayList<>();
//
//            if (!ledgers.isEmpty()) {
//                for (Ledger ledger : ledgers) {
//                    Property property = ledgerRepository.getPropertyByLedgerId(ledger.getLedger_id());
//                    if (!properties.contains(property)) {
//                        properties.add(property);
//                    }
//                    List<TransactionTests> transactions = ledgerRepository.getTransactionsByLedgerId(ledger.getLedger_id());
//                    transactionTests.addAll(transactions);
//                }
//            }
            FinancialAccount financialAccount1 = appUser.getFinancialAccount();
            List<Ledger> ledgers = financialAccount1.getLedgers();
            List<TransactionTests> transactionTests = new ArrayList<>();
            AppUserDto userDetailsDTO = new AppUserDto();
            List<Property> properties = new ArrayList<>();
            if(ledgers.isEmpty()){
                userDetailsDTO.setProperties(null);
            }else {
                for(int i = 0; i < ledgers.size(); i++){
                    List<TransactionTests> transactions = ledgers.get(i).getTransactionTests();
                    for(int j = 0; j < transactions.size(); j++){
                        transactionTests.add(transactions.get(j));
                    }
                }
                properties.add(ledgers.get(0).getProperty());
                userDetailsDTO.setProperties(properties);
            };
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
