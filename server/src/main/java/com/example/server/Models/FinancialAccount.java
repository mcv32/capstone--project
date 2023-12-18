package com.example.server.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name= "financial_account")
@Getter
@Setter
public class FinancialAccount {
    @Id
    private double account_balance;

    public FinancialAccount(double account_balance) {
        this.account_balance = account_balance;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }
}
