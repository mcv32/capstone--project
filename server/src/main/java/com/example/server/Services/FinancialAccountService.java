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

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
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
        FinancialAccount newFinancialAccount = new FinancialAccount();
        if(appUser.isPresent()) {

            //set financial account details
            newFinancialAccount.setEmail(financialAccount.getEmail());
            newFinancialAccount.setAccount_balance(financialAccount.getAccount_balance());
            newFinancialAccount.setStatus(financialAccount.getStatus());
            newFinancialAccount.setDue_date(
                    LocalDateTime.of(
                    LocalDateTime.now().getYear(),
                    Month.of(LocalDateTime.now().getMonth().getValue() + 1),
                    LocalDateTime.now().getDayOfMonth(), 0, 0));

            // add app user to be associated with financial account
            AppUser existingAppUser = appUser.get();
            List<AppUser> appUsers = new ArrayList<>();
            appUsers.add(existingAppUser);
            financialAccount.setAppUsers(appUsers);

            // update changes and save to db
            financialAccountRepository.save(newFinancialAccount);
            appUserRepository.updateFinAcctId(newFinancialAccount.getFinancial_account_id(), financialAccount.getEmail());
            appUserRepository.save(existingAppUser);
            return newFinancialAccount;
        }
        //set financial account details
        newFinancialAccount.setEmail(financialAccount.getEmail());
        newFinancialAccount.setAccount_balance(financialAccount.getAccount_balance());
        newFinancialAccount.setStatus(financialAccount.getStatus());
        newFinancialAccount.setDue_date(
                LocalDateTime.of(
                        LocalDateTime.now().getYear(),
                        Month.of(LocalDateTime.now().getMonth().getValue() + 1),
                        LocalDateTime.now().getDayOfMonth(), 0, 0));

        financialAccountRepository.save(newFinancialAccount);
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

    public FinancialAccount updateFinancialAccountDueDate(Map<String, String> requestBody){
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(Long.valueOf(requestBody.get("id")).longValue());
        if(financialAccount.isPresent()){
            FinancialAccount fa = financialAccount.get();
            int year = Integer.parseInt(requestBody.get("year"));
            Month month = Month.of(Integer.parseInt(requestBody.get("month")));
            int day = Integer.parseInt(requestBody.get("day"));
            int hour = Integer.parseInt(requestBody.get("hour"));
            int minute = Integer.parseInt(requestBody.get("minute"));
            int second = Integer.parseInt(requestBody.get("second"));
            fa.setDue_date(LocalDateTime.of(year, month, day, hour, minute, second));
            if(fa.getAccount_balance() <= 0){
                fa.setStatus("Good standing");
            }else if(fa.getAccount_balance() > 0){
                if(LocalDateTime.now().isAfter(fa.getDue_date())){
                    fa.setStatus("Overdue");
                }else{
                    fa.setStatus("Payment due soon");
                }
            }
            financialAccountRepository.save(fa);
            return fa;
        }
        return financialAccount.get();
    }



    public FinancialAccount addAnotherUser(Map<String, String> requestBody) {
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(Long.valueOf(requestBody.get("id")).longValue());
        if (financialAccount.isPresent()){
            Optional<AppUser> appUser = appUserRepository.findByEmail(requestBody.get("email"));

            if (appUser.isPresent()){
                List<AppUser> appUsers = financialAccount.get().getAppUsers();

                //add other app user if not already associated with fin acct
                if(!appUsers.contains(appUser.get()))
                    appUsers.add(appUser.get());
                financialAccount.get().setAppUsers(appUsers);
                return financialAccount.get();
            }
        }
        return null;
    }

    public List<FinancialAccount> getOverdueAccounts() {
        List<FinancialAccount> allAccounts = financialAccountRepository.findAll();
        List<FinancialAccount> overdueAccounts = new ArrayList<>();
        for(int i = 0; i < allAccounts.size(); i++){
            FinancialAccount financialAccount = allAccounts.get(i);
            if (financialAccount.getAccount_balance() > 0 && financialAccount.getDue_date().isBefore(LocalDateTime.now())) {
                overdueAccounts.add(financialAccount);
            }
        }
        return overdueAccounts;
    }

    public List<FinancialAccount> getOpenServiceTickets() {
        List<FinancialAccount> allAccounts = financialAccountRepository.findAll();
        List<FinancialAccount> openServiceTickets = new ArrayList<>();
        for(int i = 0; i < allAccounts.size(); i++){
            FinancialAccount financialAccount = allAccounts.get(i);
            if (financialAccount.getAccount_balance() > 0) {
                openServiceTickets.add(financialAccount);
            }
        }
        return openServiceTickets;
    }


}
