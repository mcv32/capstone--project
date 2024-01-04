package com.example.server.Controllers;

import com.example.server.Models.Ledger;
import com.example.server.Models.Property;
import com.example.server.Models.TransactionTests;
import com.example.server.REQUESTS.LedgerRequest;
import com.example.server.Services.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000/"}, allowedHeaders = {"Authorization"})
@RequestMapping(path = "/ledgers")
public class LedgerController {

    private final LedgerService ledgerService;

    @Autowired
    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping
    public List<Ledger> getAllLedgers() {
        return ledgerService.getAllLedgers();
    }

    @PostMapping("/getLedger")
    public Ledger getLedgerById(@RequestBody Map<String, Long> requestBody) {
        Ledger ledger = ledgerService.getLedgerById(requestBody.get("id"));
        return ledger;
    }

    @PostMapping("/create")
    public Ledger createLedger(@RequestBody LedgerRequest ledgerRequest) {
        return ledgerService.createLedger(ledgerRequest);
    }

    @PutMapping("/update")
    public Ledger updateLedger(@RequestBody Map<String, String> requestBody) {
        return ledgerService.updateLedger(requestBody);
    }

    @DeleteMapping("/delete")
    public String deleteLedger(@RequestBody Map<String, Long> requestBody) {
        ledgerService.deleteLedger(requestBody.get("id"));
        return "Ledger with ID " + requestBody.get("id") + " deleted successfully.";
    }

    @PostMapping("/payments/")
    public Ledger updateLedgerAmount(@RequestBody Map<String, String> requestBody){
        return ledgerService.updateLedgerAmount(Long.valueOf(requestBody.get("ledger_id")).longValue(),
                Double.parseDouble(requestBody.get("amount")));
    }

    @PostMapping("/update/property")
    public Ledger updateLedgerProperty(@RequestBody Map<String, Long> requestBody){
        return ledgerService.updateLedgerProperty(Long.valueOf(requestBody.get("ledger_id")).longValue(),
                Long.valueOf(requestBody.get("property_id")).longValue());
    }

    @PostMapping("/transactions")
    public TransactionTests getTransactionByLedgerId(@RequestBody Map<String, Long> requestBody){
        return ledgerService.getTransactionsByLedgerId(requestBody.get("id"));
    }

    @GetMapping("/recentPayments")
    public List<Ledger> getRecentPayments(){
        return ledgerService.getRecentPayments();
    }

    @PostMapping("/getLedgersOfProperty")
    public List<Ledger> getLedgersOfProperty(@RequestBody Map<String, Long> requestBody){
        return ledgerService.getLedgersWithProperty(requestBody);
    }

    @PostMapping("/property")
    public Property getPropertyByLedgerId(@RequestBody Map<String, Long> requestBody){
        return ledgerService.getPropertyByLedgerId(requestBody.get("id"));
    }

    @GetMapping("/overdue")
    public List<Ledger> getOverdueTickets(){
        return ledgerService.getOverDueLedgers();
    }

    @GetMapping("/openServiceTickets")
    public List<Ledger> getOpenServiceTickets(){
        return ledgerService.getOpenServiceTickets();
    }
}
