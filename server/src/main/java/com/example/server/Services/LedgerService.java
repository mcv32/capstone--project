package com.example.server.Services;

import com.example.server.Models.*;
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
            System.out.print(ledgerRequest);
            System.out.print(ledgerRequest.getLedgerType());
            System.out.println(LedgerType.valueOf(ledgerRequest.getLedgerType()));
            Optional<Property> property = propertyRepository.findById(ledgerRequest.getProperty_id());
            if(property.isPresent()){
                Property prop = property.get();

                LedgerType ledgerType;

                // update financial account balance and property profit/loss
                if(ledgerRequest.getLedgerType().equals("CHARGE")){
                    ledgerType = LedgerType.CHARGE;
                    fa.setAccount_balance(fa.getAccount_balance() + ledgerRequest.getAmount());
                    // credit type
                }else if(ledgerRequest.getLedgerType().equals("CREDIT")){
                    ledgerType = LedgerType.CREDIT;
                    fa.setAccount_balance(fa.getAccount_balance() - ledgerRequest.getAmount());

                    // payment type
                }else if(ledgerRequest.getLedgerType().equals("PAYMENT")){
                    ledgerType = LedgerType.PAYMENT;
                    fa.setAccount_balance(fa.getAccount_balance() - ledgerRequest.getAmount());
                    prop.setProperty_profit_and_loss(prop.getProperty_profit_and_loss() + ledgerRequest.getAmount());

                    // expense payment type
                }else if(ledgerRequest.getLedgerType().equals("EXPENSE")){
                    ledgerType = LedgerType.EXPENSE;
                    prop.setProperty_profit_and_loss(prop.getProperty_profit_and_loss() - ledgerRequest.getAmount());
                }else{
                    return null;
                }

                System.out.println(ledgerType);
                // update financial account status
                if(fa.getAccount_balance() <= 0){
                    fa.setStatus("Good standing");
                }else if(fa.getAccount_balance() > 0){
                    if(LocalDateTime.now().isAfter(fa.getDue_date())){
                        fa.setStatus("Overdue");
                    }else{
                        fa.setStatus("Payment due soon");
                    }
                }

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
                newLedger.setProperty(prop);
                newLedger.setFinancialAccount(fa);
                newLedger.setTime(ledgerRequest.getTime());
                newLedger.setLedgerType(ledgerType);
                newLedger.setTime(LocalDateTime.now());

                // save the changes to the repo & db
                ledgerRepository.save(newLedger);
                fa.getLedgers().add(newLedger);
                financialAccountRepository.save(fa);
                propertyRepository.save(prop);
                return newLedger;
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
            if(financialAccount.getDue_date().isBefore(LocalDateTime.now()) && allLedgers.get(i).getAmount() > 0){
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
