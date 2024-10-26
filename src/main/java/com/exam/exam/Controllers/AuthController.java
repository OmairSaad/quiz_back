package com.exam.exam.Controllers;

import com.exam.exam.Exceptions.UserBadReq;
import com.exam.exam.Models.JwtReponse;
import com.exam.exam.Models.JwtRequest;
import com.exam.exam.Payloads.PasswordChange;
import com.exam.exam.Services.UserService;
import com.exam.exam.config.JwtHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class AuthController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper helper;

    @PostMapping("/login/")
    public JwtReponse login(@RequestBody JwtRequest jwtRequest) {
        System.out.println(passwordEncoder.encode("Shoaib123"));
        this.doAuthenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        String token = this.helper.generateToken(jwtRequest.getUsername());
        JwtReponse response = new JwtReponse(token);
        return  response;
    }

    public void doAuthenticate(String username, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            throw new UserBadReq("Something Went Wrong");
        }
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(Principal pr, @RequestBody PasswordChange password){
        return userService.changePassord(pr.getName(),password);
    }
}
