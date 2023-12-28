package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@Entity
@Table(name= "financial_account")
@AllArgsConstructor
@NoArgsConstructor
public class FinancialAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "financial_account_seq")
    @SequenceGenerator(name = "financial_account_seq", sequenceName = "FINANCIAL_ACCOUNT_SEQUENCE", allocationSize = 1)
    private Long financial_account_id;
    private double account_balance;
    private String email;
    @OneToMany(
            mappedBy = "financialAccount"
    )
    private List<AppUser> appUsers = new ArrayList<>();

    public List<Ledger> getLedgers() {
        return ledgers;
    }

    public void setLedgers(List<Ledger> ledgers) {
        this.ledgers = ledgers;
    }

    @OneToMany(
            mappedBy = "financialAccount"
    )
    private List<Ledger> ledgers;

    public Long getFinancial_account_id() {
        return financial_account_id;
    }

    public void setFinancial_account_id(Long financial_account_id) {
        this.financial_account_id = financial_account_id;
    }

    public double getAccount_balance() {
        return account_balance;
    }

    public void setAccount_balance(double account_balance) {
        this.account_balance = account_balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AppUser> getAppUsers() {
        return appUsers;
    }

    public void setAppUsers(List<AppUser> appUsers) {
        this.appUsers = appUsers;
    }
}
