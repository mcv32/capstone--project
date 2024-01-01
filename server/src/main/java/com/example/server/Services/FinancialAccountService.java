package com.example.server.Services;

import com.example.server.Models.AppUser;
import com.example.server.Models.FinancialAccount;
import com.example.server.Models.Ledger;
import com.example.server.Repositories.AppUserRepository;
import com.example.server.Repositories.FinancialAccountRepository;
import lombok.AllArgsConstructor;
import net.snowflake.client.jdbc.internal.org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FinancialAccountService {

    @Autowired
    private final FinancialAccountRepository financialAccountRepository;
    @Autowired
    private final AppUserRepository appUserRepository;

    public List<FinancialAccount> getAllAccounts(){
        return financialAccountRepository.findAll();
    }

    public Optional<FinancialAccount> getFinancialAccountById(Long id) {
        return financialAccountRepository.findById(id);
    }

    public FinancialAccount createAccount(FinancialAccount financialAccount) {
        Optional<AppUser> appUser = appUserRepository.findByEmail(financialAccount.getEmail());
        FinancialAccount newFinancialAccount = financialAccountRepository.save(financialAccount);
        if(appUser.isPresent()) {
            AppUser existingAppUser = appUser.get();
            newFinancialAccount.getAppUsers().add(existingAppUser);
            appUserRepository.updateFinAcctId(newFinancialAccount.getFinancial_account_id(), financialAccount.getEmail());
            appUserRepository.save(existingAppUser);
        }
        financialAccountRepository.save(newFinancialAccount);
        System.out.println("here " + newFinancialAccount);
        return newFinancialAccount;
    }

    public FinancialAccount updateFinancialAccount(Map<String, String> bodyRequest) {
        financialAccountRepository.updateFinancialAccount(
                Double.parseDouble(bodyRequest.get("account_balance")),
                bodyRequest.get("email"),
                Long.valueOf(bodyRequest.get("id")).longValue()
        );
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(Long.valueOf(bodyRequest.get("id")).longValue());
        return financialAccount.get();
    }

    public void deleteFinancialAccount(Long id) {
        financialAccountRepository.deleteFinancialAccount(id);
    }

    public List<AppUser> getAppUsersByFinancialAccountId(Long id) {
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(id);
        if(financialAccount.isPresent()) return financialAccount.get().getAppUsers();
        return null;
    }

    public List<Ledger> getLedgersByFinancialAccountId(Long id) {
        return financialAccountRepository.findLedgersByFinancialAccountId(id);
    }

    public FinancialAccount addAnotherUser(Map<String, String> requestBody) {
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(Long.valueOf(requestBody.get("id")).longValue());
        if (financialAccount.isPresent()){
            Optional<AppUser> appUser = appUserRepository.findByEmail(requestBody.get("email"));

            if (appUser.isPresent()){
                List<AppUser> appUsers = financialAccount.get().getAppUsers();

                if(!appUsers.contains(appUser.get())) appUsers.add(appUser.get());
                financialAccount.get().setAppUsers(appUsers);
                return financialAccount.get();
            }
        }
        return null;
    }
}
