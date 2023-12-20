package com.example.server.Repositories;

import com.example.server.Models.AppUser;
import com.example.server.Models.AppUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepository extends JpaRepository<AppUser, String> {
    Optional<AppUser> findByEmail(String email);

    @Query(value = "INSERT INTO APP_USER (ID, F_NAME, L_NAME, EMAIL, PASSWORD, APP_USER_ROLE, LOCKED, ENABLED) " +
            "VALUES (:ID, :F_NAME, :L_NAME, :EMAIL, :PASSWORD, :APP_USER_ROLE, :LOCKED, :ENABLED)", nativeQuery = true)
    AppUser createAppUser(
            @Param("ID") Long id,
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
}
