package com.example.server.Services;

import com.example.server.Models.AppUser;
import com.example.server.Models.FinancialAccount;
import com.example.server.Models.Ledger;
import com.example.server.Repositories.AppUserRepository;
import com.example.server.Repositories.FinancialAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public FinancialAccount updateFinancialAccount(Long id, FinancialAccount updatedFinancialAccount) {
        return financialAccountRepository.updateFinancialAccount(updatedFinancialAccount.getAccount_balance(), updatedFinancialAccount.getEmail(), id);
    }

    public void deleteFinancialAccount(Long id) {
        financialAccountRepository.deleteFinancialAccount(id);
    }

    public List<AppUser> getAppUsersByFinancialAccountId(Long id) {
        return financialAccountRepository.findById(id).get().getAppUsers();
    }

    public List<Ledger> getLedgersByFinancialAccountId(Long id) {
        return financialAccountRepository.findLedgersByFinancialAccountId(id);
    }
}
