package com.exam.exam.Controllers;

import com.exam.exam.Models.UsernameAndAuthority;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@CrossOrigin
@RestController
public class Authorities {

    @GetMapping("/authorities")
    public ResponseEntity<?> authorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || !authentication.isAuthenticated()) {
            return  new ResponseEntity<>("User is not authenticated!", HttpStatus.UNAUTHORIZED);
        }

        //Extract Authorities
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return new ResponseEntity<>(new UsernameAndAuthority(authentication.getName(),authorities), HttpStatus.OK);
    }

}
