package com.example.server.Services;

import com.example.server.Models.*;
import com.example.server.REQUESTS.LedgerRequest;
import com.example.server.Repositories.FinancialAccountRepository;
import com.example.server.Repositories.LedgerRepository;
import com.example.server.Repositories.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

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
        System.out.println(ledgerRequest.getProperty_id());
        System.out.println(ledgerRequest);
        System.out.println("is expense: " + ledgerRequest.getLedgerType().equals("EXPENSE"));
            Optional<Property> property = propertyRepository.findById(ledgerRequest.getProperty_id());
        System.out.println(property);
            if(property.isPresent()){
                System.out.println("present");
                Property prop = property.get();

                LedgerType ledgerType;
                if(ledgerRequest.getFinancial_account_id() != null) {
                    Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(ledgerRequest.getFinancial_account_id());
                    if (financialAccount.isPresent()) {
                        System.out.println("fin account");
                        FinancialAccount fa = financialAccount.get();
                        // update financial account balance and property profit/loss
                        if (ledgerRequest.getLedgerType().equals("CHARGE")) {
                            ledgerType = LedgerType.CHARGE;
                            fa.setAccount_balance(fa.getAccount_balance() + ledgerRequest.getAmount());
                            // credit type
                        } else if (ledgerRequest.getLedgerType().equals("CREDIT")) {
                            ledgerType = LedgerType.CREDIT;
                            fa.setAccount_balance(fa.getAccount_balance() - ledgerRequest.getAmount());

                            // payment type
                        } else if (ledgerRequest.getLedgerType().equals("PAYMENT")) {
                            ledgerType = LedgerType.PAYMENT;
                            fa.setAccount_balance(fa.getAccount_balance() - ledgerRequest.getAmount());
                            prop.setProperty_profit_and_loss(prop.getProperty_profit_and_loss() + ledgerRequest.getAmount());

                            // expense payment type
                        } else {
                            return null;
                        }

                        System.out.println(ledgerType);
                        // update financial account status
                        if (fa.getAccount_balance() <= 0) {
                            fa.setStatus("Good standing");
                        } else if (fa.getAccount_balance() > 0) {
                            if (LocalDateTime.now().isAfter(fa.getDue_date())) {
                                fa.setStatus("Overdue");
                            } else {
                                fa.setStatus("Payment due soon");
                            }
                        }

                        // update property profit/loss
                        if (prop.getProperty_profit_and_loss() < 0) {
                            prop.setStatus("Loss");
                        } else if (prop.getProperty_profit_and_loss() == 0) {
                            prop.setStatus("Even");
                        } else {
                            prop.setStatus("Profit");
                        }


                        // set ledger fields
                        newLedger.setAmount(ledgerRequest.getAmount());
                        newLedger.setDescription(ledgerRequest.getDescription());
                        newLedger.setProperty(prop);
                        newLedger.setFinancialAccount(fa);
                        newLedger.setLedgerType(ledgerType);
                        newLedger.setTime(LocalDateTime.now());
                        newLedger.setStatus(true);

                        // save the changes to the repo & db
                        ledgerRepository.save(newLedger);
                        fa.getLedgers().add(newLedger);
                        financialAccountRepository.save(fa);
                        propertyRepository.save(prop);
                        return newLedger;
                    }
                }
                System.out.println("expense");
                // if no financial account this is an expense
                if(ledgerRequest.getLedgerType().equals("EXPENSE")){
                    ledgerType = LedgerType.EXPENSE;
                    prop.setProperty_profit_and_loss(prop.getProperty_profit_and_loss() - ledgerRequest.getAmount());

                    // update property profit/loss
                    if(prop.getProperty_profit_and_loss() < 0){
                        prop.setStatus("Loss");
                    }else if(prop.getProperty_profit_and_loss() == 0){
                        prop.setStatus("Even");
                    }else{
                        prop.setStatus("Profit");
                    }

                    // set ledger fields
                    newLedger.setAmount(ledgerRequest.getAmount());
                    newLedger.setDescription(ledgerRequest.getDescription());
                    newLedger.setFinancialAccount(null);
                    newLedger.setProperty(prop);
                    newLedger.setLedgerType(ledgerType);
                    newLedger.setTime(LocalDateTime.now());
                    newLedger.setStatus(true);

                    // save the changes to the repo & db
                    ledgerRepository.save(newLedger);
                    propertyRepository.save(prop);
                    return newLedger;
                }else{
                    return null;
                }
        }
        return null;
    }

    public Ledger updateLedger(Map<String, String> requestBody) {

        Optional<Ledger> l = ledgerRepository.findById(Long.valueOf(requestBody.get("id")).longValue());
        if(l.isPresent()) {
            Ledger ledger = l.get();
            Optional<Property> prop = propertyRepository.findById(Long.valueOf(requestBody.get("property_id")).longValue());
            if(prop.isPresent()){
                Optional<FinancialAccount> fa = financialAccountRepository.findById(Long.valueOf(requestBody.get("financial_account_id")).longValue());
                if(fa.isPresent()){


                    ledgerRepository.updateLedger(Long.valueOf(requestBody.get("id")).longValue(),
                            Double.parseDouble(requestBody.get("amount")),
                            Long.valueOf(requestBody.get("financial_account_id")).longValue(),
                            Long.valueOf(requestBody.get("property_id")).longValue(),
                            requestBody.get("description"),
                            LocalDateTime.parse(requestBody.get("time")),
                            requestBody.get("ledger_type")
                    );

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

    public TransactionTests getTransactionsByLedgerId(Long id){
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
            FinancialAccount financialAccount = allLedgers.get(i).getFinancialAccount();
            if(financialAccount.getDue_date().isBefore(LocalDateTime.now()) && financialAccount.getAccount_balance() > 0){
                overdueLedgers.add(allLedgers.get(i));
            }
        }
        return overdueLedgers;
    }

    public List<Ledger> getLedgersWithProperty(Map<String, Long> requestBody){
        Long id = requestBody.get("id");
        List<Ledger> allLedgers = ledgerRepository.findAll();
        List<Ledger> propertyLedgers = new ArrayList<>();
        for(int i = 0; i < allLedgers.size(); i++){
            Ledger ledger = allLedgers.get(i);
            if( (ledger.getProperty().getProperty_id() == id) &&
                    (ledger.getLedgerType() == LedgerType.EXPENSE || ledger.getLedgerType() == LedgerType.PAYMENT)){
                propertyLedgers.add(ledger);
            }
        }
        return propertyLedgers;
    }

    public List<Ledger> getOpenServiceTickets(){

        List<Ledger> allLedgers = ledgerRepository.findAll();
        List<Ledger> openServiceLedgers = new ArrayList<>();
        for(int i = 0; i < allLedgers.size(); i++){
            FinancialAccount financialAccount = allLedgers.get(i).getFinancialAccount();
            if(financialAccount.getAccount_balance() > 0.0){
                openServiceLedgers.add(allLedgers.get(i));
            }
        }
        return openServiceLedgers;
    }

    public List<Ledger> getRecentPayments() {
        List<Ledger> allLedgers = ledgerRepository.findAll();
        List<Ledger> payments = new ArrayList<>();
        // filter all payment ledgers
        for(int i = 0; i < allLedgers.size(); i++){
            Ledger curLedger = allLedgers.get(i);
            if(curLedger.getLedgerType() == LedgerType.PAYMENT){
                payments.add(curLedger);
            }
        }

        // sort by most recent
        payments.sort( (p1, p2) -> p2.getTime().compareTo(p1.getTime()) );

        return payments;
    }
}
