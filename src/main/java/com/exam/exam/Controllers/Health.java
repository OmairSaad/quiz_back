package com.exam.exam.Controllers;

import com.exam.exam.Payloads.PasswordChange;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/health")
public class Health {
    @GetMapping
    public String health() {
        return "OK";
    }
}
