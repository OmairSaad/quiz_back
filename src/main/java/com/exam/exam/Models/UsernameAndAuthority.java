package com.exam.exam.Models;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsernameAndAuthority {
    private String username;
    private Collection<? extends GrantedAuthority> Authorities;
    public UsernameAndAuthority(String username, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.Authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Authorities = authorities;
    }
}
