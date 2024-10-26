package com.exam.exam.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class jwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtHelper helper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
     String authHeader = request.getHeader("Authorization");
        System.out.println(authHeader);
     String username = null;
     String token = null;
     if (authHeader != null && authHeader.startsWith("Bearer")) {
         token = authHeader.substring(7);
         try{
             username = helper.extractUserName(token);
         }catch (Exception e){
             e.printStackTrace();
             System.out.println("Error");
         }


     }else{
         System.out.println("Authorization header not found");
     }
     if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

         UserDetails us = userDetailsService.loadUserByUsername(username);
         boolean validateToken = helper.validateToken(token,us);
         if(validateToken) {
             UsernamePasswordAuthenticationToken authtoken = new UsernamePasswordAuthenticationToken(us, null, us.getAuthorities());
             authtoken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
             SecurityContextHolder.getContext().setAuthentication(authtoken);
         }
     }else{
         System.out.println("Token is not valid");
     }
     filterChain.doFilter(request, response);
    }
}
