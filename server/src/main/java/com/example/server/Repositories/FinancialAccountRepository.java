package com.example.server.Repositories;

import com.example.server.Models.AppUser;
import com.example.server.Models.FinancialAccount;
import com.example.server.Models.Ledger;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FinancialAccountRepository extends JpaRepository<FinancialAccount, Long> {

    Optional<FinancialAccount> findById(Long id);

    Optional<FinancialAccount> findByEmail(String email);

    // get app users associated with a financial account
    @Query(value = "SELECT * FROM App_User au WHERE au.financial_account_id = :id", nativeQuery = true)
    List<AppUser> findAppUsersByFinancialAccountId(@Param("id") Long id);

    // get ledger associated with financial account
    @Query(value = "SELECT * FROM Ledger l WHERE l.financial_account_id = :id", nativeQuery = true)
    List<Ledger> findLedgersByFinancialAccountId(@Param("id") Long id);

    @Query(value = "INSERT INTO FINANCIAL_ACCOUNT( ACCOUNT_BALANCE, EMAIL) VALUES(:ACCOUNT_BALANCE, :EMAIL)", nativeQuery = true)
    FinancialAccount createFinancialAccount(
            @Param("ACCOUNT_BALANCE") double account_balance,
            @Param("EMAIL") String email
    );

    @Override
    <S extends FinancialAccount> S save(S entity);

    // update financial account
    @Modifying
    @Query(value = "UPDATE FINANCIAL_ACCOUNT SET ACCOUNT_BALANCE = :ACCOUNT_BALANCE, EMAIL = :EMAIL WHERE FINANCIAL_ACCOUNT_ID = :id", nativeQuery = true)
    int updateFinancialAccount(
            @Param("ACCOUNT_BALANCE")double accountBalance,
            @Param("EMAIL") String email,
            @Param("id") Long id);

    @Query(value = "DELETE FROM FINANCIAL_ACCOUNT WHERE FINANCIAL_ACCOUNT_ID = :ID", nativeQuery = true)
    void deleteFinancialAccount(
            @Param("ID") Long id);

}
