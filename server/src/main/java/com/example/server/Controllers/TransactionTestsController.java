package com.example.server.Controllers;

import com.example.server.Models.TransactionTests;
import com.example.server.REQUESTS.TransactionRequest;
import com.example.server.Services.TransactionTestsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = {"Authorization"})
@RequestMapping(path= "/transactions")
public class TransactionTestsController {

    private final TransactionTestsService transactionTestsService;

    @Autowired
    public TransactionTestsController(TransactionTestsService transactionTestsService){
        this.transactionTestsService = transactionTestsService;
    }


    @GetMapping("/getTransaction")
    public TransactionTests getTransactionTestsById(@RequestBody Map<String, Long> requestBody){
        TransactionTests transactionTests = transactionTestsService.getTransactionsTestsById(requestBody.get("id"));
        return transactionTests;
    }

    @PostMapping("/create")
    public String createTransactionTest(@RequestBody TransactionRequest transactionRequest) {
        return transactionTestsService.createTransactionTest(transactionRequest);
    }

//    @PutMapping("/{id}")
//    public TransactionTests updateTransactionTest(@PathVariable Long id, @RequestBody TransactionTests updatedTransactionTest) {
//        return transactionTestsService.updateTransactionTest(id, updatedTransactionTest);
//    }

    @DeleteMapping("/delete")
    public String deleteTransactionTest(@RequestBody Map<String, Long> requestBody) {
        transactionTestsService.deleteTransactionTest(requestBody.get("id"));
        return "TransactionTest with ID " + requestBody.get("id") + " deleted successfully.";
    }
}
