package com.example.server.Services;

import com.example.server.Exceptions.EmailAlreadyExists;
import com.example.server.Models.AppUser;
import com.example.server.Repositories.AppUserRepository;
import com.example.server.login.registration.token.ConfirmationToken;
import com.example.server.login.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {


    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
    private final AppUserRepository appUserRepository;
    private final ConfirmationTokenService confirmationTokenService;
    @Autowired
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser){
        boolean userExists = appUserRepository
                .findByEmail(appUser.getEmail())
                .isPresent();
        if (userExists){
            // TODO check if attributes are the same and
            // TODO If email not confirmed, send confirmation email again
//            return "Email already taken";
            throw new EmailAlreadyExists("Email Already Exists");
        }

        String encodedPassword = passwordEncoder
                .encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();
//        Generate confirmation Token
        ConfirmationToken confirmationToken = new ConfirmationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                appUser
        );
//        Save confirmation to DB
        confirmationTokenService.saveConfirmationToken(confirmationToken);

//TODO: Send Email

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public List<AppUser> getAllAccounts() {

        return appUserRepository.findAll();
    }

    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
}
