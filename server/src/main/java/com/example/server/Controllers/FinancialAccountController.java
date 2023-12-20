package com.example.server.Controllers;

import com.example.server.Models.FinancialAccount;
import com.example.server.Services.FinancialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/financialAccounts")
public class FinancialAccountController {
    private final FinancialAccountService financialAccountService;

    @Autowired
    public FinancialAccountController(FinancialAccountService financialAccountService) {
        this.financialAccountService = financialAccountService;
    }

    @GetMapping
    public List<FinancialAccount> getAllFinancialAccounts(){

        return financialAccountService.getAllAccounts();
    }

    @PostMapping
    public FinancialAccount getFinancialAccountById(@RequestBody int id){
        FinancialAccount financialAccount = financialAccountService.getFinancialAccountById(id);
        return financialAccount;
    }
}
