package com.fundoonotesapp.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor   // for jackson deserilzation
@AllArgsConstructor  // for creating object for that easily
@JsonIgnoreProperties(ignoreUnknown = true)
public class CachedUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<String> authorities;
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}