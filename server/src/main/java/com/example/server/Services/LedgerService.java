package com.example.server.Services;

import com.example.server.Models.FinancialAccount;
import com.example.server.Models.Ledger;
import com.example.server.Models.Property;
import com.example.server.Models.TransactionTests;
import com.example.server.REQUESTS.LedgerRequest;
import com.example.server.Repositories.FinancialAccountRepository;
import com.example.server.Repositories.LedgerRepository;
import com.example.server.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LedgerService {

    @Autowired
    private final LedgerRepository ledgerRepository;
    @Autowired
    private final FinancialAccountRepository financialAccountRepository;
    @Autowired
    private final PropertyRepository propertyRepository;

    @Autowired
    public LedgerService(LedgerRepository ledgerRepository, FinancialAccountRepository financialAccountRepository, PropertyRepository propertyRepository) {
        this.ledgerRepository = ledgerRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.propertyRepository = propertyRepository;
    }

    public List<Ledger> getAllLedgers() {
        return ledgerRepository.findAll();
    }

    public Ledger getLedgerById(Long id) {

        Optional<Ledger> ledger = ledgerRepository.findById(id);
        if(ledger.isPresent()){
            return ledger.get();
        }
        return null;
    }

    public Ledger createLedger(LedgerRequest ledgerRequest) {
        Ledger newLedger = new Ledger();
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(ledgerRequest.getFinancial_account_id());
        if(financialAccount.isPresent()){
            FinancialAccount fa = financialAccount.get();
            newLedger.setAmount(ledgerRequest.getAmount());
            newLedger.setDescription(ledgerRequest.getDescription());
            System.out.println(ledgerRequest);
            Optional<Property> property = propertyRepository.findById(ledgerRequest.getProperty_id());
            if(property.isPresent()){
                fa.setAccount_balance(fa.getAccount_balance() + ledgerRequest.getAmount());
                newLedger.setProperty(property.get());
                newLedger.setFinancialAccount(fa);
                newLedger.setRecurring(ledgerRequest.isRecurring());
                newLedger.setRecurringDate(ledgerRequest.getRecurring_date());
                newLedger.setStatus(ledgerRequest.isStatus());
                ledgerRepository.save(newLedger);
                fa.getLedgers().add(newLedger);
                return newLedger;
            }

        }
        return null;
    }

    public Ledger updateLedger(Map<String, String> requestBody) {
        ledgerRepository.updateLedger(Long.valueOf(requestBody.get("id")).longValue(),
                Double.parseDouble(requestBody.get("amount")),
                Boolean.valueOf(requestBody.get("status")),
                Long.valueOf(requestBody.get("financial_account_id")).longValue(),
                Long.valueOf(requestBody.get("property_id")).longValue(),
                requestBody.get("description"),
                Boolean.valueOf(requestBody.get("recurring")),
                LocalDateTime.parse(requestBody.get("recurring_date"))
                );
        Optional<Ledger> l = ledgerRepository.findById(Long.valueOf(requestBody.get("id")).longValue());
        if(l.isPresent()) {
            Ledger ledger = l.get();
            Optional<Property> prop = propertyRepository.findById(Long.valueOf(requestBody.get("property_id")).longValue());
            if(prop.isPresent()){
                Optional<FinancialAccount> fa = financialAccountRepository.findById(Long.valueOf(requestBody.get("financial_account_id")).longValue());
                if(fa.isPresent()){
                    ledger.setProperty(prop.get());
                    ledger.setFinancialAccount(fa.get());
                    return ledger;
                }

            }

        }
        return null;
    }

    public void deleteLedger(Long id) {
        ledgerRepository.deleteById(id);
    }

    public Ledger updateLedgerAmount(Long id, double amount){
        Optional<Ledger> ledger = ledgerRepository.findById(id);
        if(ledger.isPresent()){
            Ledger l = ledger.get();
            ledgerRepository.updateLedgerAmount(amount, id);
        }
        return null;
    }

    public Ledger updateLedgerProperty(Long id, Long property_id){
        ledgerRepository.updateLedgerProperty(property_id, id);
        Optional<Ledger> ledger = ledgerRepository.findById(id);
        if(ledger.isPresent()){
            Ledger l = ledger.get();
            Optional<Property> property = propertyRepository.findById(property_id);
            if(property.isPresent()) {
                l.setProperty(property.get());
                return l;
            }
        }
        return null;

    }

    public List<TransactionTests> getTransactionsByLedgerId(Long id){
        Optional<Ledger> ledger=  ledgerRepository.findById(id);
        return ledger.get().getTransactionTests();
    }

    public Property getPropertyByLedgerId(Long id){
        Optional<Ledger> l = ledgerRepository.findById(id);
        return l.get().getProperty();
    }

    public List<Ledger> getOverDueLedgers(){

        List<Ledger> allLedgers = ledgerRepository.findAll();
        List<Ledger> overdueLedgers = new ArrayList<>();
        for(int i = 0; i < allLedgers.size(); i++){
            if(allLedgers.get(i).getRecurringDate().isBefore(LocalDateTime.now())){
                overdueLedgers.add(allLedgers.get(i));
            }
        }
        return overdueLedgers;
    }

    public List<Ledger> getOpenServiceTickets(){

        List<Ledger> allLedgers = ledgerRepository.findAll();
        List<Ledger> openServiceLedgers = new ArrayList<>();
        for(int i = 0; i < allLedgers.size(); i++){
            if(allLedgers.get(i).getAmount() > 0){
                openServiceLedgers.add(allLedgers.get(i));
            }
        }
        return openServiceLedgers;
    }
}
