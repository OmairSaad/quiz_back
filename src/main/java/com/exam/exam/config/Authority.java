package com.exam.exam.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;


public class Authority implements GrantedAuthority {
    private  String authority;
    public Authority(String authority) {
        this.authority = authority;
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}
