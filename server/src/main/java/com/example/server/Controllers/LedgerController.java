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

    @GetMapping("/getLedger")
    public Ledger getLedgerById(@RequestBody Map<String, Long> requestBody) {
        Ledger ledger = ledgerService.getLedgerById(requestBody.get("id"));
        return ledger;
    }

    @PostMapping("/create")
    public Ledger createLedger(@RequestBody LedgerRequest ledgerRequest) {
        return ledgerService.createLedger(ledgerRequest);
    }

    @PutMapping("/update")
    public Ledger updateLedger(@RequestBody Ledger updatedLedger) {
        return ledgerService.updateLedger(updatedLedger.getLedger_id(), updatedLedger);
    }

    @DeleteMapping("/delete")
    public String deleteLedger(@RequestBody Map<String, Long> requestBody) {
        ledgerService.deleteLedger(requestBody.get("id"));
        return "Ledger with ID " + requestBody.get("id") + " deleted successfully.";
    }

    @PostMapping("/payments/{id}")
    public Ledger updateLedgerAmount(@PathVariable Long id, @RequestBody double amount){
        return ledgerService.updateLedgerAmount(id, amount);
    }

    @PostMapping("/update/{id}")
    public Ledger updateLedgerProperty(@PathVariable Long id){
        return ledgerService.updateLedgerProperty(id);
    }

    @PostMapping("/transactions/{id}")
    public List<TransactionTests> getTransactionsByLedgerId(@PathVariable Long id){
        return ledgerService.getTransactionsByLedgerId(id);
    }

    @PostMapping("/property/{id}")
    public Property getPropertyByLedgerId(@PathVariable Long id){
        return ledgerService.getPropertyByLedgerId(id);
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
