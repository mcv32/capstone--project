package com.example.server.Controllers;

import com.example.server.Models.TransactionTests;
import com.example.server.Services.TransactionTestsService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public TransactionTests getTransactionTestsById(@PathVariable int id){
        TransactionTests transactionTests = transactionTestsService.getTransactionsTestsById(id);
        return transactionTests;
    }

    @PostMapping
    public TransactionTests createTransactionTest(@RequestBody TransactionTests transactionTest) {
        return transactionTestsService.createTransactionTest(transactionTest);
    }

    @PutMapping("/{id}")
    public TransactionTests updateTransactionTest(@PathVariable int id, @RequestBody TransactionTests updatedTransactionTest) {
        return transactionTestsService.updateTransactionTest(id, updatedTransactionTest);
    }

    @DeleteMapping("/{id}")
    public String deleteTransactionTest(@PathVariable int id) {
        transactionTestsService.deleteTransactionTest(id);
        return "TransactionTest with ID " + id + " deleted successfully.";
    }
}
