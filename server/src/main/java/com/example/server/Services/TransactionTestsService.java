package com.example.server.Services;

import com.example.server.Models.TransactionTests;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionTestsService {

    private final List<TransactionTests> transactionTestsList = new ArrayList<>();

    public List<TransactionTests> getAllTransactionTests() {
        return transactionTestsList;
    }

    public TransactionTests getTransactionsTestsById(int id) {
        Optional<TransactionTests> optionalTransactionTests = transactionTestsList.stream()
                .filter(transactionTest -> transactionTest.getTransaction_id() == id)
                .findFirst();
        return optionalTransactionTests.orElse(null);
    }

    public TransactionTests createTransactionTest(TransactionTests transactionTest) {
        int nextId = transactionTestsList.isEmpty() ? 1 : transactionTestsList.get(transactionTestsList.size() - 1).getTransaction_id() + 1;
        transactionTest.setTransaction_id(nextId);
        transactionTestsList.add(transactionTest);
        return transactionTest;
    }

    public TransactionTests updateTransactionTest(int id, TransactionTests updatedTransactionTest) {
        for (int i = 0; i < transactionTestsList.size(); i++) {
            if (transactionTestsList.get(i).getTransaction_id() == id) {
                updatedTransactionTest.setTransaction_id(id);
                transactionTestsList.set(i, updatedTransactionTest);
                return updatedTransactionTest;
            }
        }
        return null;
    }

    public void deleteTransactionTest(int id) {
        transactionTestsList.removeIf(transactionTest -> transactionTest.getTransaction_id() == id);
    }
}
