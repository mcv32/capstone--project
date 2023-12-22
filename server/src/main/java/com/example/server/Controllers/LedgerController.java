package com.example.server.Controllers;

import com.example.server.Models.Ledger;
import com.example.server.Services.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/{id}")
    public Ledger getLedgerById(@PathVariable int id) {
        Ledger ledger = ledgerService.getLedgerById(id);
        return ledger;
    }

    @PostMapping
    public Ledger createLedger(@RequestBody Ledger ledger) {
        return ledgerService.createLedger(ledger);
    }

    @PutMapping("/{id}")
    public Ledger updateLedger(@PathVariable int id, @RequestBody Ledger updatedLedger) {
        return ledgerService.updateLedger(id, updatedLedger);
    }

    @DeleteMapping("/{id}")
    public String deleteLedger(@PathVariable int id) {
        ledgerService.deleteLedger(id);
        return "Ledger with ID " + id + " deleted successfully.";
    }
}
