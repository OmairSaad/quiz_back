package com.exam.exam.Services;

import com.exam.exam.Exceptions.UserBadReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.exam.exam.Models.User;
import com.exam.exam.Repository.UserRepository;

@Service
public class CustomeUserdetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user==null){
            System.out.println("User not found");
            throw new UserBadReq("User not found");
        }
        return new CustomeUserdetails(user);
    }
}
