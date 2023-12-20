package com.example.server.Models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name= "app_user")
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AppUser implements UserDetails {

    @Id
    private String email;
    private String fName;
    private String lName;
    private  String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String phoneNumber = "";

    public AppUser(String fName,
                   String lName,
                   String email,
                   String password,
                   AppUserRole appUserRole) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(appUserRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
