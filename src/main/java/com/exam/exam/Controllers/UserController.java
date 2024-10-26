package com.exam.exam.Controllers;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.exam.exam.Payloads.PasswordChange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.exam.Models.Role;
import com.exam.exam.Models.User;
import com.exam.exam.Models.UserRole;
import com.exam.exam.Services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) throws Exception{
        Role r = new Role();
        r.setRoleName("NORMAL");
        UserRole us = new UserRole();
        us.setRole(r);
        Set<UserRole> sr = new HashSet<>();
        sr.add(us);
        us.setUser(user);
        User t = userService.createUser(user,sr);
        return  new ResponseEntity<>(t, HttpStatus.CREATED);
    }
    

    @GetMapping("/")
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @GetMapping("/{username}")
    public User getByUsername(@PathVariable String username) throws Exception{
        return this.userService.getByUsername(username);
    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal pr) throws Exception{
        return userService.getByUsername(pr.getName());
    }

    @PutMapping("/")
    public User updatUser(Principal pr, @RequestBody User user){
        return this.userService.updateUser(pr.getName(),user);
    }

}
