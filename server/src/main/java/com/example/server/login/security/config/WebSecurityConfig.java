package com.example.server.login.security.config;

import com.example.server.Repositories.AppUserRepository;
import com.example.server.Services.AppUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class  WebSecurityConfig {

    private final AuthenticationProvider authenticaionProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v*/registration/**", "/authenticate", "/payments", "/users").permitAll()
                        .anyRequest().authenticated()
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticaionProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
//        return http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                                .requestMatchers( "/api/v*/registration/**", "/login").permitAll()
//                                .requestMatchers("/payments").permitAll()
//                                .anyRequest()
//                                .authenticated()
//                                .and()
//                        .sessionManagement()
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                        .and()
//                        .authenticationProvider(authenticaionProvider)
//                        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//                )
//                .userDetailsService(appUserService)
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults())
//                .build();
    }
}


