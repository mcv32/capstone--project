package com.example.server.Repositories;

import com.example.server.Models.AppUser;
import com.example.server.Models.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByEmail(String email);

    @Query(value = "INSERT INTO APP_USER (F_NAME, L_NAME, EMAIL, PASSWORD, APP_USER_ROLE, LOCKED, ENABLED) " +
            "VALUES (:F_NAME, :L_NAME, :EMAIL, :PASSWORD, :APP_USER_ROLE, :LOCKED, :ENABLED)", nativeQuery = true)
    AppUser createAppUser(
            @Param("F_NAME") String fName,
            @Param("L_NAME") String lName,
            @Param("EMAIL") String email,
            @Param("PASSWORD") String password,
            @Param("APP_USER_ROLE") AppUserRole appUserRole,
            @Param("LOCKED") Boolean locked,
            @Param("ENABLED") Boolean enabled);



    @Transactional
    @Modifying
    @Query(value = "UPDATE APP_USER SET ENABLED = TRUE WHERE EMAIL = :EMAIL", nativeQuery = true)
    int enableAppUser(@Param("EMAIL") String email);

    @Modifying
    @Query(value = "UPDATE APP_USER SET F_NAME = :FIRSTNAME, L_NAME = :LASTNAME, PHONE_NUMBER = :PHONE_NUMBER, EMAIL = :EMAIL WHERE EMAIL = :OLD_EMAIL", nativeQuery = true)
    AppUser updateAppUser(
            @Param("FIRSTNAME") String firstName,
            @Param("LASTNAME") String lastName,
            @Param("PHONE_NUMBER") String phoneNumber,
            @Param("EMAIL") String email,
            @Param("OLD_EMAIL") String oldEmail
    );

    @Modifying
    @Query(value = "UPDATE APP_USER SET FINANCIAL_ACCOUNT_ID = :id WHERE EMAIL = :EMAIL", nativeQuery = true)
    int updateFinAcctId(
            @Param("id") Long financialAccountId,
            @Param("EMAIL") String email);
    @Modifying
    @Query(value = "UPDATE APP_USER SET APP_USER_ROLE = :role WHERE EMAIL = :EMAIL", nativeQuery = true)
    AppUser updateUserRole(
            @Param("role") String role,
            @Param("EMAIL") String email
    );

    @Query (value = "DELETE FROM APP_USER WHERE EMAIL = :EMAIL", nativeQuery = true)
    void deleteUser(
            @Param("EMAIL") String email
    );
}
