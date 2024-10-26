package com.exam.exam.Services;

import java.util.List;
import java.util.Set;

import com.exam.exam.Models.User;
import com.exam.exam.Models.UserRole;
import com.exam.exam.Payloads.PasswordChange;
import org.springframework.http.ResponseEntity;

public interface UserService {
    
    public User createUser(User user, Set<UserRole> role) throws Exception;

    public List<User> getAllUsers();

    public User getByUsername(String username) throws Exception;

    public User updateUser(String username, User user);

    ResponseEntity<?> changePassord(String name, PasswordChange password);
}
