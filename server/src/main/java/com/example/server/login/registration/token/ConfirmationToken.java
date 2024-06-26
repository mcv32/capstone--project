package com.example.server.login.registration.token;

import com.example.server.Models.AppUser;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ConfirmationToken {

    @Id
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @Column (nullable = false)
    private LocalDateTime expiredAt;
    private LocalDateTime confirmedAt;
    @ManyToOne
    @JoinColumn(name = "EMAIL", referencedColumnName = "EMAIL")
    @JsonBackReference
    private AppUser appUser;

    public ConfirmationToken(String token,
                             LocalDateTime createdAt,
                             LocalDateTime expiredAt,
                             AppUser appUser) {
        this.token = token;
        this.createdAt = createdAt;
        this.expiredAt = expiredAt;
        this.appUser = appUser;
    }
}
