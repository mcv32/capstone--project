package com.example.server.Repositories;

import com.example.server.Models.Property;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface PropertyRepository extends JpaRepository <Property, Long> {


    Optional<Property> findById(Long id
    );


    @Query(value = "DELETE PROPERTY WHERE PROPERTY_ID = :ID", nativeQuery = true)
    void deleteById(
            @Param("ID") Long id
    );

    @Query(value = "INSERT INTO PROPERTY (NAME, ADDRESS_STREET, ADDRESS_LINE_2, CITY, STATE, ZIP, PROPERTY_PROFIT_AND_LOSS)" +
            "VALUES( :NAME, :ADDRESS_STREET, :ADDRESS_LINE_2, :CITY, :STATE, :ZIP, :PROPERTY_BALANCE)", nativeQuery = true)
    Property createProperty(
            @Param("NAME") String name,
            @Param("ADDRESS_STREET") String address,
            @Param("ADDRESS_LINE_2") String address2,
            @Param("CITY") String city,
            @Param("STATE") String state,
            @Param("ZIP") String zip,
            @Param("PROPERTY_BALANCE") double amount
    );

    @Modifying
    @Query(value = "UPDATE PROPERTY SET NAME = :NAME, ADDRESS_STREET = :ADDRESS_STREET, ADDRESS_LINE_2 = :ADDRESS_LINE_2, " +
            "CITY = :CITY, STATE = :STATE, ZIP = :ZIP, STATUS = :STATUS, " +
            "PROPERTY_PROFIT_AND_LOSS = :PROPERTY_BALANCE WHERE PROPERTY_ID = :ID", nativeQuery = true)
    int updateProperty(
            @Param("ID") Long id,
            @Param("NAME") String name,
            @Param("ADDRESS_STREET") String address,
            @Param("ADDRESS_LINE_2") String address2,
            @Param("CITY") String city,
            @Param("STATE") String state,
            @Param("ZIP") String zip,
            @Param("STATUS") String status,
            @Param("PROPERTY_BALANCE") double amount
    );
}
