package com.example.server.Models;

import com.example.server.login.registration.token.ConfirmationToken;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Builder
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
    private String f_name;
    private String l_name;
    private  String password;
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String phone_number = "";
    @ManyToOne
    @JoinColumn(
            nullable = true,
            name = "financial_account_id"
    )
    private FinancialAccount financialAccount;
    @OneToMany(mappedBy = "appUser")
    private List<ConfirmationToken> confirmationTokens;
    public AppUser(String f_name,
                   String l_name,
                   String email,
                   String password,
                   AppUserRole appUserRole) {
        this.f_name = f_name;
        this.l_name = l_name;
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
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setFinancialAccount(FinancialAccount financialAccount){
        this.financialAccount = financialAccount;
    }
    public FinancialAccount getFinancialAccount(){
        return this.financialAccount;
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
