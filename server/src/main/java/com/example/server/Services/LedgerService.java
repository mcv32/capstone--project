package com.example.server.Services;

import com.example.server.Models.FinancialAccount;
import com.example.server.Models.Ledger;
import com.example.server.Models.Property;
import com.example.server.Models.TransactionTests;
import com.example.server.Repositories.FinancialAccountRepository;
import com.example.server.Repositories.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LedgerService {

    @Autowired
    private final LedgerRepository ledgerRepository;
    private final FinancialAccountRepository financialAccountRepository;

    @Autowired
    public LedgerService(LedgerRepository ledgerRepository, FinancialAccountRepository financialAccountRepository) {
        this.ledgerRepository = ledgerRepository;
        this.financialAccountRepository = financialAccountRepository;
    }

    public List<Ledger> getAllLedgers() {
        return ledgerRepository.findAll();
    }

    public Ledger getLedgerById(Long id) {
        return ledgerRepository.findById(id);
    }

    public Ledger createLedger(Ledger ledger) {
        Ledger newLedger = ledgerRepository.createLedger(ledger.getAmount(), ledger.isStatus(),
                ledger.getFinancial_account_id(),
                ledger.getProperty_id(),
                ledger.getDescription(),
                ledger.isRecurring(),
                ledger.getRecurringDate());
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(ledger.getFinancial_account_id());
        if(financialAccount.isPresent()) financialAccount.get().getLedgers().add(newLedger);
        return newLedger;
    }

    public Ledger updateLedger(Long id, Ledger updatedLedger) {
        return ledgerRepository.updateLedger(id, updatedLedger.getAmount(), updatedLedger.isStatus(),
                updatedLedger.getFinancial_account_id(),
                updatedLedger.getProperty_id(),
                updatedLedger.getDescription(), updatedLedger.isRecurring(), updatedLedger.getRecurringDate());
    }

    public void deleteLedger(Long id) {
        ledgerRepository.deleteById(id);
    }

    public Ledger updateLedgerAmount(Long id, double amount){
        Ledger ledger = ledgerRepository.findById(id);
        double updatedAmount = ledger.getAmount() - amount;
        return ledgerRepository.updateLedgerAmount(updatedAmount, id);
    }

    public Ledger updateLedgerProperty(Long id){
        Ledger ledger = ledgerRepository.findById(id);
        return ledgerRepository.updateLedgerProperty(ledger.getProperty_id(), id);
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
