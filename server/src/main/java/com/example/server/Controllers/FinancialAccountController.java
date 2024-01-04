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
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = {"Authorization"})
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

    @PutMapping("/addAnotherUser")
    public FinancialAccount addAnotherUser(@RequestBody Map<String, String > requestBody){
        return financialAccountService.addAnotherUser(requestBody);
    }

    @PutMapping("/update")
    public FinancialAccount updateFinancialAccount(@RequestBody Map<String, String> requestBody){
        return financialAccountService.updateFinancialAccount(requestBody);
    }

    @DeleteMapping("/delete")
    public String deleteFinancialAccount(@RequestBody Map<String, Long> requestBody){
        financialAccountService.deleteFinancialAccount(requestBody.get("id"));
        return "Financial Account with ID: " + requestBody.get("id") + " deleted successfully.";
    }

    // get app users tied to a financial account id
    @PostMapping("/appUsers")
    public List<AppUser> getAppUsersByFinancialAccountId(@RequestBody Map<String, Long> requestBody){
        return financialAccountService.getAppUsersByFinancialAccountId(requestBody.get("id"));
    }

    // get ledgers from financial account id
    @PostMapping("/ledgers")
    public List<Ledger> getLedgersByFinancialAccountId(@RequestBody Map<String, Long> requestBody){
        return financialAccountService.getLedgersByFinancialAccountId(requestBody.get("id"));
    }

    @GetMapping("/overdue")
    public List<FinancialAccount> getOverdue(){
        return financialAccountService.getOverdueAccounts();
    }

    @GetMapping("/openServiceTickets")
    public List<FinancialAccount> getOpenServiceTickets(){
        return financialAccountService.getOpenServiceTickets();
    }

    @PostMapping("/updateFinancialAccountDueDate")
    public FinancialAccount updateFinancialAccountDueDate(@RequestBody Map<String, String> requestBody){
        return financialAccountService.updateFinancialAccountDueDate(requestBody);
    }

    @GetMapping
    public List<FinancialAccount> getAllFinancialAccounts(){
        return financialAccountService.getAllAccounts();
    }

}
