package com.example.server.Repositories;


import com.example.server.Models.PaymentType;
import com.example.server.Models.TransactionTests;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
@Transactional
public interface TransactionTestsRepository extends JpaRepository <TransactionTests, Integer> {


    @Query(value = "SELECT * FROM TRANSACTION_TESTS WHERE TRANSACTION_ID = :ID", nativeQuery = true)
    TransactionTests findById(
            @Param("ID") Long id);

    @Query(value = "INSERT INTO TRANSACTION_TESTS (AMOUNT, FINANCIAL_ACCOUNT_ID, LEDGER_ID, PAYMENT_TYPE, CARD_NUMBER, TIME, STATUS) " +
            "VALUES (:AMOUNT, :FIN_ACCT_ID, :LEDGER_ID, :PAYMENT_TYPE, :CARD_NUMBER, :TIME, :STATUS)", nativeQuery = true)
    TransactionTests createTransaction(
            @Param("AMOUNT")double amount,
            @Param("FIN_ACCT_ID") Long financialAccountId,
            @Param("LEDGER_ID") Long ledgerId,
            @Param("PAYMENT_TYPE")PaymentType paymentType,
            @Param("CARD_NUMBER") String cardNumber,
            @Param("TIME")LocalDateTime time,
            @Param("STATUS") boolean status
            );

    @Query(value = "DELETE FROM TRANSACTION_TESTS WHERE TRANSACTION_ID = :ID", nativeQuery = true)
    TransactionTests deleteTransaction(
            @Param("ID") Long id);

    @Modifying
    @Query(value = "UPDATE TRANSACTION_TESTS SET AMOUNT = :AMOUNT, FINANCIAL_ACCOUNT_ID = :FIN_ACCT_ID," +
            "LEDGER_ID = :LEDGER_ID WHERE TRANSACTION_ID = :ID", nativeQuery = true)
    TransactionTests updateTransaction(
            @Param("ID") Long id,
            @Param("AMOUNT")double amount,
            @Param("FIN_ACCT_ID") Long financialAccountId,
            @Param("LEDGER_ID") Long ledgerId
    );
}
