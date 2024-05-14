package com.example.facebook_be.controller;

import com.example.facebook_be.dto.AccountRequest;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        Account user = accountService.login(loginRequest.getIdentifier(), loginRequest.getPassword());
        if (user != null) {
            session.setAttribute("user", user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.badRequest().body("SĐT hoặc email hoặc mật khẩu không đúng !");
        }
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout successful");
    }


}
