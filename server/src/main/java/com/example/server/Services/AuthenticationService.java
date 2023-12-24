package com.example.server.Services;

import com.example.server.Controllers.AuthenticationRequest;
import com.example.server.Controllers.AuthenticationResponse;
import com.example.server.Exceptions.EmailDoesntExist;
import com.example.server.Exceptions.IncorrectPassword;
import com.example.server.Repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AppUserRepository repository;
    private final JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private AuthenticationRequest request;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    public AuthenticationResponse register(RegistrationRequest registrationRequest){
//        var user = User.builder()
//                .username(request.getEmail())
//                .password(passwordEncoder.bCryptPasswordEncoder().encode(request.getPassword()))
//                .build();
//        repository.save(user);
//        var jwtToken = jwtService.generateToken(user);
//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();
//        return null;
//    }
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        System.out.println(request);
        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new EmailDoesntExist("Email not found"));

//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new IncorrectPassword("Incorrect Password");
        }

        var jwtToken = jwtService.generateToken(user);
        System.out.println(user.getAppUserRole());
        return AuthenticationResponse.builder()
                .role(user.getAppUserRole())
                .token(jwtToken)
                .build();
    }
}
