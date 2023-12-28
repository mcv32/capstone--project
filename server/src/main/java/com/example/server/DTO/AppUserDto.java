package com.example.server.DTO;

import com.example.server.Models.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor 
@NoArgsConstructor
public class AppUserDto {
    private AppUser appUser;
    private FinancialAccount financialAccount;
    private List<Ledger> ledgers;
    private List<TransactionTests> transactions;
    private List<Property> properties;


    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public FinancialAccount getFinancialAccount() {
        return financialAccount;
    }

    public void setFinancialAccount(FinancialAccount financialAccount) {
        this.financialAccount = financialAccount;
    }

    public List<Ledger> getLedgers() {
        return ledgers;
    }

    public void setLedgers(List<Ledger> ledgers) {
        this.ledgers = ledgers;
    }

    public List<TransactionTests> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionTests> transactions) {
        this.transactions = transactions;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
}
