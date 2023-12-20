package com.example.server.login.registration.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {

    Optional<ConfirmationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE CONFIRMATION_TOKEN SET CONFIRMED_AT = :CONFIRMED_AT WHERE TOKEN = :TOKEN", nativeQuery = true)
    int updateConfirmedAt(@Param("TOKEN")  String token,@Param("CONFIRMED_AT") LocalDateTime confirmedAt);
}
