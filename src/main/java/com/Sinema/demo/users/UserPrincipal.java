package com.Sinema.demo.users;

import com.Sinema.demo.tokens.ValidationToken;
import com.Sinema.demo.tokens.ValidationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private User user;
    private ValidationToken validationToken;
    
    @Autowired
    private ValidationTokenRepository validationTokenRepository;

    public UserPrincipal(User user){
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        this.validationToken = validationTokenRepository.findById(user.getId()).get();
        if(user.getRole().equals("WaitingValidation") && validationToken.getExpirationDateTime().isAfter(LocalDateTime.now())){
            //account expired since it wasn't activated in time
            return false;
        }
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if(user.getRole().equals("banned")){
            return false;
        }
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isAccountNonLocked();
    }
}
