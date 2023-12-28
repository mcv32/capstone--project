package com.example.server.Services;

import com.example.server.Models.Ledger;
import com.example.server.Models.TransactionTests;
import com.example.server.REQUESTS.TransactionRequest;
import com.example.server.Repositories.LedgerRepository;
import com.example.server.Repositories.TransactionTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionTestsService {

    @Autowired
    private final TransactionTestsRepository transactionTestsRepository;
    private final LedgerRepository ledgerRepository;

    @Autowired
    public TransactionTestsService(TransactionTestsRepository transactionTestsRepository, LedgerRepository ledgerRepository) {
        this.transactionTestsRepository = transactionTestsRepository;
        this.ledgerRepository = ledgerRepository;
    }


    public TransactionTests getTransactionsTestsById(Long id) {
        return transactionTestsRepository.findById(id);
    }

    public TransactionTests createTransactionTest(TransactionRequest transactionRequest) {

        Long ledgerId = transactionRequest.getLedger_id(); // Extract ledger_id from the request body

        // Fetch Ledger based on the received ledgerId
        Optional<Ledger> ledgerOptional = ledgerRepository.findById(ledgerId);

        if (ledgerOptional.isPresent()) {
            Ledger ledger = ledgerOptional.get();
            double amount = ledger.getAmount() - transactionRequest.getAmount();
            // Create a new TransactionTests instance
            TransactionTests transaction = new TransactionTests();
            transaction.setAmount(transactionRequest.getAmount());
            transaction.setAccount_id(transactionRequest.getFinancial_account_id());
            transaction.setPaymentType(transactionRequest.getPaymentType());
            transaction.setCardNumber(transactionRequest.getCardNumber());
            transaction.setTime(transactionRequest.getTime());
            transaction.setStatus(transactionRequest.isStatus());
            transaction.setLedger(ledger); // Associate Ledger with TransactionTests

            // Save TransactionTests entity
            transactionTestsRepository.save(transaction);
            return transaction;
        }
        return null;
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
