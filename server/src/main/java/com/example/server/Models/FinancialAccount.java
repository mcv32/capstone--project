package com.example.server.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name= "financial_account")
public class FinancialAccount {
    @SequenceGenerator(
            name = "financial_account_sequence",
            sequenceName = "financial_account_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "financial_account_sequence"
    )
    private int financial_account_id;
    private double account_balance;
    private String email;
    @OneToMany(
            mappedBy = "financialAccount"
    )
    private List<AppUser> appUsers;

    public FinancialAccount(double account_balance, String email, List<AppUser> appUsers) {
        this.account_balance = account_balance;
        this.email = email;
        this.appUsers = appUsers;
    }

}
