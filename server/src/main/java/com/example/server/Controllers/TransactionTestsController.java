package com.example.server.Controllers;

import com.example.server.Models.TransactionTests;
import com.example.server.Services.TransactionTestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/transactionTests")
public class TransactionTestsController {

    private final TransactionTestsService transactionTestsService;

    @Autowired
    public TransactionTestsController(TransactionTestsService transactionTestsService){
        this.transactionTestsService = transactionTestsService;
    }

    @GetMapping
    public List<TransactionTests> getAllTransactionTests(){
        return transactionTestsService.getAllTransactionTests();
    }

    @PostMapping
    public TransactionTests getTransactionTestsById(@RequestBody int id){
        TransactionTests transactionTests = transactionTestsService.getTransactionsTestsById(id);
        return transactionTests;
    }
}
