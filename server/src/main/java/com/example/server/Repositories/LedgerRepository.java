package com.example.server.Repositories;

import com.example.server.Models.Ledger;
import com.example.server.Models.Property;
import com.example.server.Models.TransactionTests;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface LedgerRepository extends JpaRepository <Ledger, Long>{
    Optional<Ledger> findById(Long id);

    // delete ledger by id
    @Query(value = "DELETE LEDGER WHERE LEDGER_ID = :ID", nativeQuery = true)
    void deleteById(
            @Param("ID") Long id
    );

    // create new ledger
    @Query(value = "INSERT INTO LEDGER (AMOUNT, DESCRIPTION, RECURRING, RECURRING_DATE, STATUS, ACCOUNT_ID, PROPERTY_ID" +
            "VALUES (:AMOUNT, :DESCRIPTION, :RECURRING, :RECURRING_DATE, :STATUS, :ACCOUNT_ID, :PROPERTY_ID)", nativeQuery = true)
    Ledger createLedger(
            @Param("AMOUNT") double amount,
            @Param("STATUS") boolean status,
            @Param("ACCOUNT_ID") Long financialAccountId,
            @Param("PROPERTY_ID") Long propertyId,
            @Param("DESCRIPTION") String description,
            @Param("RECURRING") boolean recurring,
            @Param("RECURRING_DATE") LocalDateTime recurringDate);

    // update fields changed by user
    @Modifying
    @Query(value = "UPDATE LEDGER SET AMOUNT = :AMOUNT, STATUS = :STATUS, " +
            "ACCOUNT_ID = :ACCOUNT_ID, PROPERTY_ID = :PROPERTY_ID, " +
            "DESCRIPTION = :DESCRIPTION, RECURRING = :RECURRING, " +
            "RECURRING_DATE = :RECURRING_DATE WHERE LEDGER_ID = :ID", nativeQuery = true)
    Ledger updateLedger(
            @Param("ID") Long id,
            @Param("AMOUNT") double amount,
            @Param("STATUS") boolean status,
            @Param("ACCOUNT_ID") Long financialAccountId,
            @Param("PROPERTY_ID") Long propertyId,
            @Param("DESCRIPTION") String description,
            @Param("RECURRING") boolean recurring,
            @Param("RECURRING_DATE") LocalDateTime recurringDate);


    // update the amount of the ledger
    @Modifying
    @Query(value = "UPDATE LEDGER SET AMOUNT = :AMOUNT WHERE LEDGER_ID = :ID", nativeQuery = true)
    Ledger updateLedgerAmount(
            @Param("AMOUNT") double amount,
            @Param("ID") Long id
    );

    // update the property associated with the ledger
    @Modifying
    @Query(value = "UPDATE LEDGER SET PROPERTY_ID = :PROPERTY_ID WHERE LEDGER_ID = :ID", nativeQuery = true)
    Ledger updateLedgerProperty(
            @Param("PROPERTY_ID") Long property_id,
            @Param("ID") Long id
    );


    // get transactions associated with each ledger
    @Query(value = "SELECT * FROM TRANSACTION_TESTS WHERE LEDGER_ID = :ID", nativeQuery = true)
    List<TransactionTests>  getTransactionsByLedgerId(
      @Param("ID") Long id
    );

    // get property associated with the ledger
    @Query(value = "SELECT * FROM PROPERTY WHERE PROPERTY_ID = :ID", nativeQuery = true)
    Property getPropertyByLedgerId(
            @Param("ID") Long id
    );

    @Query(value = "SELECT * FROM LEDGER WHERE AMOUNT > 0 AND RECURRING_DATE < LOCALDATE.NOW()", nativeQuery = true)
    List<Ledger> getOverDueLedgers(
    );

    @Query(value = "SELECT * FROM LEDGER WHERE AMOUNT > 0 AND RECURRING_DATE > LOCALDATE.NOW()", nativeQuery = true)
    List<Ledger> getOpenServiceTickets();
}
