package com.example.server.Controllers;

import com.example.server.Models.AppUser;
import com.example.server.Models.FinancialAccount;
import com.example.server.Models.Ledger;
import com.example.server.Services.FinancialAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path= "/financialAccounts")
public class FinancialAccountController {
    private final FinancialAccountService financialAccountService;

    @Autowired
    public FinancialAccountController(FinancialAccountService financialAccountService) {
        this.financialAccountService = financialAccountService;
    }

    @PostMapping("/getFinancialAccount")
    public ResponseEntity<Optional<FinancialAccount>> getFinancialAccount(@RequestBody Map<String, Long> requestBody){
        Optional<FinancialAccount> financialAccount = financialAccountService.getFinancialAccountById( requestBody.get("id"));
        return ResponseEntity.ok(financialAccount);
    }
    @PostMapping("/create")
    public FinancialAccount createFinancialAccount(@RequestBody FinancialAccount financialAccount){
        return financialAccountService.createAccount(financialAccount);
    }

    @PutMapping("/update")
    public FinancialAccount updateFinancialAccount(@RequestBody FinancialAccount updatedFinancialAccount){
        return financialAccountService.updateFinancialAccount(updatedFinancialAccount.getFinancial_account_id(), updatedFinancialAccount);
    }

    @DeleteMapping("/delete")
    public String deleteFinancialAccount(@RequestBody Map<String, Long> requestBody){
        financialAccountService.deleteFinancialAccount(requestBody.get("id"));
        return "Financial Account with ID: " + requestBody.get("id") + " deleted successfully.";
    }

    @PostMapping("/appUsers")
    public List<AppUser> getAppUsersByFinancialAccountId(@RequestBody Map<String, Long> requestBody){
        return financialAccountService.getAppUsersByFinancialAccountId(requestBody.get("id"));
    }

    @PostMapping("/ledgers")
    public List<Ledger> getLedgersByFinancialAccountId(@RequestBody Map<String, Long> requestBody){
        return financialAccountService.getLedgersByFinancialAccountId(requestBody.get("id"));
    }

    @GetMapping
    public List<FinancialAccount> getAllFinancialAccounts(){
        return financialAccountService.getAllAccounts();
    }

}
