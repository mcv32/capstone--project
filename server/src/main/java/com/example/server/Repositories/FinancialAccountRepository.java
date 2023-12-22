package com.example.server.Repositories;

import com.example.server.Models.FinancialAccount;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Integer> {
    Optional<FinancialAccount> findById(int financial_account_id);
}
