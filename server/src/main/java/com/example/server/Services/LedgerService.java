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

import java.util.List;
import java.util.Optional;

@Service
public class LedgerService {

    @Autowired
    private final LedgerRepository ledgerRepository;
    private final FinancialAccountRepository financialAccountRepository;
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

    public Ledger updateLedger(Long id, Ledger updatedLedger) {
        return ledgerRepository.updateLedger(id, updatedLedger.getAmount(), updatedLedger.getStatus(),
                updatedLedger.getFinancialAccount().getFinancial_account_id(),
                updatedLedger.getProperty().getProperty_id(),
                updatedLedger.getDescription(), updatedLedger.isRecurring(), updatedLedger.getRecurringDate());
    }

    public void deleteLedger(Long id) {
        ledgerRepository.deleteById(id);
    }

    public Ledger updateLedgerAmount(Long id, double amount){
        Optional<Ledger> ledger = ledgerRepository.findById(id);
        if(ledger.isPresent()){
            Ledger l = ledger.get();
            double updatedAmount = l.getAmount() - amount;
            return ledgerRepository.updateLedgerAmount(updatedAmount, id);
        }
        return null;
    }

    public Ledger updateLedgerProperty(Long id){
        Optional<Ledger> ledger = ledgerRepository.findById(id);
        if(ledger.isPresent()){
            Ledger l = ledger.get();
            return ledgerRepository.updateLedgerProperty(l.getProperty().getProperty_id(), id);
        }
        return null;

    }

    public List<TransactionTests> getTransactionsByLedgerId(Long id){
        return ledgerRepository.getTransactionsByLedgerId(id);
    }

    public Property getPropertyByLedgerId(Long id){
        return ledgerRepository.getPropertyByLedgerId(id);
    }

    public List<Ledger> getOverDueLedgers(){
        return ledgerRepository.getOverDueLedgers();
    }

    public List<Ledger> getOpenServiceTickets(){
        return ledgerRepository.getOpenServiceTickets();
    }
}
