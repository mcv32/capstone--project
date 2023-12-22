package com.example.server.Controllers;

import com.example.server.Models.FinancialAccount;
import com.example.server.Services.FinancialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<FinancialAccount> getFinancialAccountById(@RequestBody int id){
        Optional<FinancialAccount> financialAccount = financialAccountService.getFinancialAccountById(id);
        return financialAccount;
    }
}
