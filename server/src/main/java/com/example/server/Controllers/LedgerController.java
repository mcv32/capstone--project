package com.example.server.Controllers;

import com.example.server.Models.Ledger;
import com.example.server.Services.LedgerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path= "/ledgers")
public class LedgerController {

    private final LedgerService ledgerService;

    @Autowired
    public LedgerController(LedgerService ledgerService){
        this.ledgerService = ledgerService;
    }

    @GetMapping
    public List<Ledger> getAllLedgers(){
        return ledgerService.getAllAccounts();
    }

    @PostMapping
    public Ledger getLedgerById(@RequestBody int id){
        Ledger ledger = ledgerService.getLedgerById(id);
        return ledger;
    }
}
