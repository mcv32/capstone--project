package com.example.server.Services;

import com.example.server.Models.TransactionTests;
import com.example.server.Repositories.TransactionTestsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionTestsService {

    private final TransactionTestsRepository transactionTestsRepository;

    @Autowired
    public TransactionTestsService(TransactionTestsRepository transactionTestsRepository){
        this.transactionTestsRepository = transactionTestsRepository;
    }

    public List<TransactionTests> getAllTransactionTests(){
        return transactionTestsRepository.findAll();
    }

    public TransactionTests getTransactionsTestsById(int id) {
        return transactionTestsRepository.findById(id);
    }
}
