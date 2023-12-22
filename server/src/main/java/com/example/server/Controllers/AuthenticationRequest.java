package com.example.server.Controllers;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class AuthenticationRequest {
    private String email;
    private String password;
}
