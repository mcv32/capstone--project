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
    @Autowired
    private final FinancialAccountRepository financialAccountRepository;
    private final LedgerRepository ledgerRepository;
    private final TransactionTestsRepository transactionTestsRepository;
    private final PropertyRepository propertyRepository;
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

    public AppUser updateAppUser(String email, AppUser appUser){
        return appUserRepository.updateAppUser(appUser.getF_name(), appUser.getL_name(), appUser.getPhone_number(), appUser.getEmail(), email);
    }
    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public void updateUserRole(String role, String email){
        appUserRepository.updateUserRole(role, email);
    }

    public void deleteUser(String email) {
        appUserRepository.deleteUser(email);
    }

    public AppUserDto getAppUserDetailsByEmail(String email) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByEmail(email);
        if (optionalAppUser.isPresent()) {
            AppUser appUser = optionalAppUser.get();
            FinancialAccount financialAccount = appUser.getFinancialAccount();
            List<Ledger> ledgers = financialAccount != null ? financialAccount.getLedgers() : Collections.emptyList();
            List<TransactionTests> transactionTests = new ArrayList<>();
            List<Property> properties = new ArrayList<>();

            if (!ledgers.isEmpty()) {
                for (Ledger ledger : ledgers) {
                    Property property = ledgerRepository.getPropertyByLedgerId(ledger.getLedger_id());
                    if (!properties.contains(property)) {
                        properties.add(property);
                    }
                    List<TransactionTests> transactions = ledgerRepository.getTransactionsByLedgerId(ledger.getLedger_id());
                    transactionTests.addAll(transactions);
                }
            }

            AppUserDto userDetailsDTO = new AppUserDto();
            userDetailsDTO.setAppUser(appUser);
            userDetailsDTO.setFinancialAccount(financialAccount);
            userDetailsDTO.setLedgers(ledgers);
            userDetailsDTO.setTransactions(transactionTests);
            userDetailsDTO.setProperties(properties);
            // Set other fields in the DTO

            return userDetailsDTO;
        }
        return null; // Or throw an exception if the user is not found
    }


}
