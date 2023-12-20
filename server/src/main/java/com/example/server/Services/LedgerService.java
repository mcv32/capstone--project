package com.example.server.Services;

import com.example.server.Models.Ledger;
import com.example.server.Repositories.LedgerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    @Autowired
    public LedgerService(LedgerRepository ledgerRepository){
        this.ledgerRepository = ledgerRepository;
    }
    public List<Ledger> getAllAccounts() {
        return ledgerRepository.findAll();
    }

    public Ledger getLedgerById(int id) {
        return ledgerRepository.findById(id);
    }
}
