package com.example.server.Services;

import com.example.server.Models.Ledger;
import com.example.server.Models.TransactionTests;
import com.example.server.Repositories.LedgerRepository;
import com.example.server.Repositories.TransactionTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public TransactionTests createTransactionTest(TransactionTests transactionTest) {
        Ledger ledger = ledgerRepository.findById(transactionTest.getLedger_id());

        double amount = ledger.getAmount() - transactionTest.getAmount();
        ledgerRepository.updateLedgerAmount(amount,
                transactionTest.getLedger_id());
        TransactionTests transaction = transactionTestsRepository.createTransaction(transactionTest.getAmount(),
                transactionTest.getFinancial_account_id(),
                transactionTest.getLedger_id(),
                transactionTest.getPaymentType(),
                transactionTest.getCardNumber(),
                transactionTest.getTime(),
                transactionTest.isStatus());
        ledger.getTransactionTests().add(transaction);
        transaction.setLedger(ledger);
        return transaction;
    }

    public TransactionTests updateTransactionTest(Long id, TransactionTests updatedTransactionTest) {
        return transactionTestsRepository.updateTransaction(id,
                updatedTransactionTest.getAmount(),
                updatedTransactionTest.getFinancial_account_id(),
                updatedTransactionTest.getLedger_id());
    }

    public void deleteTransactionTest(Long id) {
        transactionTestsRepository.deleteTransaction(id);
    }
}
