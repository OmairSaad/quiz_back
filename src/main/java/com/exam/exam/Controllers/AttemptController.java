package com.exam.exam.Controllers;

import com.exam.exam.Models.Attempt;
import com.exam.exam.Models.User;
import com.exam.exam.Services.AttemptService;
import com.exam.exam.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("attempts/")
@CrossOrigin
public class AttemptController {
    @Autowired
    private AttemptService attemptService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> getAttemptService() {
        return ResponseEntity.ok(attemptService.getAllAttempts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttemptById(@PathVariable Long id) {
        return ResponseEntity.ok(attemptService.getAttemptById(id));
    }

    @PostMapping("/")
    public ResponseEntity<?> addAttempt(@RequestBody Attempt attempt) {
        return new ResponseEntity<>(attemptService.addAttempt(attempt), HttpStatus.CREATED);
    }
    @GetMapping("/{username}/")
    public ResponseEntity<?> getAttemptByUsername(@PathVariable String username) throws Exception {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok(attemptService.getByUserName(user));
    }

    @GetMapping("/{username}/date")
    public ResponseEntity<?> getAttemptByUsernameAndDate(@PathVariable String username) throws Exception {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok(attemptService.getByUserAndDate(user));
    }

    @GetMapping("/{username}/marks")
    public ResponseEntity<?> getAttemptByUsernameAndMarks(@PathVariable String username) throws Exception {
        User user = userService.getByUsername(username);
        return ResponseEntity.ok(attemptService.getByUserAndMaxMarks(user));
    }
}
