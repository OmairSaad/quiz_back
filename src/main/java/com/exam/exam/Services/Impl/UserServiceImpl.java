package com.exam.exam.Services.Impl;

import java.util.*;

import com.exam.exam.Payloads.PasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exam.exam.Exceptions.UserBadReq;
import com.exam.exam.Models.User;
import com.exam.exam.Models.UserRole;
import com.exam.exam.Repository.RoleRepository;
import com.exam.exam.Repository.UserRepository;
import com.exam.exam.Services.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository rolRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Creating User  
    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception{
        User local = userRepo.findByUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(local==null){
            for(UserRole ur:userRoles){
               rolRepo.save(ur.getRole());
            }
            user.setUserRoles(userRoles);
            local = userRepo.save(user);
        }else{
            throw new UserBadReq("User Already Exist!!");
        }
        return local;
    }

    //Get All Users
    @Override
    public List<User> getAllUsers() {
        return this.userRepo.findAll();
    }

    //Get By Username
    @Override
    public User getByUsername(String username)  {
       User user = this.userRepo.findByUsername(username);
       user.setPassword("");
       if(user!=null){
           return user;
        }else{
            throw new UserBadReq("User not found!!");
       }
       
    }

    //Update User
    @Override
    public User updateUser(String username, User user) {
       User oldUser = this.userRepo.findByUsername(username);
       if(oldUser==null) throw new UserBadReq("User not found!!");
       oldUser.setPhone(Optional.ofNullable(user.getPhone()).filter(e-> !e.isEmpty()).orElse(oldUser.getPhone()));
       oldUser.setEmail(Optional.ofNullable(user.getEmail()).filter(e-> !e.isEmpty()).orElse(oldUser.getEmail()));
       oldUser.setFirstname(Optional.ofNullable(user.getFirstname()).filter(e-> !e.isEmpty()).orElse(oldUser.getFirstname()));
       oldUser.setLastname(Optional.ofNullable(user.getLastname()).filter(e->!e.isEmpty()).orElse(oldUser.getLastname()));
       return this.userRepo.save(oldUser);
       
    }

    @Override
    public ResponseEntity<?> changePassord(String name, PasswordChange password) {
        Map<String,String> res = new HashMap<>();
        if(password.newPassword()==null || password.newPassword().trim().isEmpty()){
            throw new UserBadReq("New Password is empty!!");
        }
        User user = userRepo.findByUsername(name);
        if(user==null) throw new UserBadReq("User not found!!");
        if(!password.repeatPassword().equals(password.newPassword())){
            throw new UserBadReq("Confirm Password does not match!!");
        }
        if(passwordEncoder.matches(password.oldPassword(), user.getPassword())){
            user.setPassword(passwordEncoder.encode(password.newPassword()));
        }else{
            throw new UserBadReq("Password not match!!");
        }
        userRepo.save(user);
        res.put("message","Password changed!!");
        return ResponseEntity.ok(res);
    }


}
