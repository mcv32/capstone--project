package com.example.server.Services;

import com.example.server.Models.*;
import com.example.server.REQUESTS.TransactionRequest;
import com.example.server.Repositories.FinancialAccountRepository;
import com.example.server.Repositories.LedgerRepository;
import com.example.server.Repositories.PropertyRepository;
import com.example.server.Repositories.TransactionTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionTestsService {

    @Autowired
    private final TransactionTestsRepository transactionTestsRepository;
    private final LedgerRepository ledgerRepository;
    private final PropertyRepository propertyRepository;
    private final FinancialAccountRepository financialAccountRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public TransactionTestsService(TransactionTestsRepository transactionTestsRepository, LedgerRepository ledgerRepository, PropertyRepository propertyRepository, FinancialAccountRepository financialAccountRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.transactionTestsRepository = transactionTestsRepository;
        this.ledgerRepository = ledgerRepository;
        this.propertyRepository = propertyRepository;
        this.financialAccountRepository = financialAccountRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public TransactionTests getTransactionsTestsById(Long id) {
        Optional<TransactionTests> transaction = transactionTestsRepository.findById(id);
        if(transaction.isPresent())
            return transaction.get();
        return null;
    }

    public String createTransactionTest(TransactionRequest transactionRequest) {
        Optional<FinancialAccount> financialAccount = financialAccountRepository.findById(transactionRequest.getFinancial_account_id());

            if(financialAccount.isPresent()){
                FinancialAccount fa = financialAccount.get();
                Optional<Property> property = propertyRepository.findById(transactionRequest.getProperty_id());
                if(property.isPresent()){

                    String sentCardNumber = transactionRequest.getCardNumber();
                    String cardNumber = sentCardNumber.substring(sentCardNumber.length() - 4, sentCardNumber.length());

                    Property prop = property.get();
                    Ledger ledger = new Ledger();

                    // change financial account balance
                    double fin_acct_amt = fa.getAccount_balance() - transactionRequest.getAmount();
                    fa.setAccount_balance(fin_acct_amt);

                    //change property balance
                    prop.setProperty_profit_and_loss(prop.getProperty_profit_and_loss() + transactionRequest.getAmount());

                    // update financial account status
                    if(fa.getAccount_balance() <= 0){
                        fa.setStatus("Good standing");
                    }else if(fa.getAccount_balance() > 0){
                        if(LocalDateTime.now().isAfter(fa.getDue_date())){
                            fa.setStatus("Overdue");
                        }else{
                            fa.setStatus("Awaiting payment");
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

                    // save property changes to db
                    propertyRepository.save(prop);

                    // set ledger fields and save to db
                    ledger.setFinancialAccount(fa);
                    ledger.setProperty(prop);
                    ledger.setAmount(-1 * transactionRequest.getAmount());
                    ledger.setTime(LocalDateTime.now());
                    ledger.setDescription("Web Portal Payment");
                    ledger.setStatus(true);
                    ledger.setLedgerType(LedgerType.PAYMENT);
                    ledgerRepository.save(ledger);


                    // Create a new TransactionTests instance and save to db
                    TransactionTests transaction = new TransactionTests();
                    transaction.setAmount(transactionRequest.getAmount());
                    transaction.setAccount_id(transactionRequest.getFinancial_account_id());
                    transaction.setPaymentType(transactionRequest.getPaymentType());
//                    transaction.setCardNumber(bCryptPasswordEncoder.encode(transactionRequest.getCardNumber()));
                    transaction.setLast_four_digits(cardNumber);
                    transaction.setEncrypted_number(bCryptPasswordEncoder.encode(transactionRequest.getCardNumber()));
                    transaction.setTime(transactionRequest.getTime());
                    transaction.setStatus(transactionRequest.isStatus());
                    transaction.setLedger(ledger); // Associate Ledger with TransactionTests
                    transactionTestsRepository.save(transaction);

                    // associate transaction with ledger, add ledger to fin acct ledgers list,
                    // save fin acct changes to db
                    ledger.setTransactionTests(transaction);
                    List<Ledger> ledgers = fa.getLedgers();
                    ledgers.add(ledger);
                    fa.setLedgers(ledgers);
                    financialAccountRepository.save(fa);
                    return "successful";
                }


            }
        return "unsuccessful";
    }

//    public TransactionTests updateTransactionTest(Long id, TransactionTests updatedTransactionTest) {
//        return transactionTestsRepository.updateTransaction(id,
//                updatedTransactionTest.getAmount(),
//                updatedTransactionTest.getFinancial_account_id(),
//                updatedTransactionTest.getLedger_id());
//    }

    public void deleteTransactionTest(Long id) {
        transactionTestsRepository.deleteTransaction(id);
    }
}
