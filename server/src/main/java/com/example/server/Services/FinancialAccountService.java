package com.example.server.Services;

import com.example.server.Models.FinancialAccount;
import com.example.server.Repositories.FinancialAccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
@AllArgsConstructor
public class FinancialAccountService {

    private final FinancialAccountRepository financialAccountRepository;

    public List<FinancialAccount> getAllAccounts(){
        return financialAccountRepository.findAll();
    }

    public FinancialAccount getFinancialAccountById(int id) {
        return financialAccountRepository.findById(id);
    }
}
