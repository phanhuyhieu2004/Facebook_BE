package com.example.facebook_be.controller.api;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Visibility;
import com.example.facebook_be.repository.IAccountRepository;
import com.example.facebook_be.service.VisibilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("apiAccountController")

@CrossOrigin(origins = "*")
public class AccountController {
    @Autowired
    private IAccountRepository iAccountRepository;

    @PostMapping("/api/login")
    public ResponseEntity<Account> login(@RequestBody Account loginRequest, HttpSession session) {
        Account account = iAccountRepository.findByEmailOrPhone(loginRequest.getEmail(), loginRequest.getPhone());
        if (account == null || !account.getPassword().equals(loginRequest.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            // Lưu tài khoản vào session
            session.setAttribute("loggedInAccount", account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<Account> register(@RequestBody Account registerRequest) {
        // Kiểm tra sự tồn tại của email và số điện thoại trong cơ sở dữ liệu
        Optional<Account> existingEmailAccount = Optional.ofNullable(iAccountRepository.findByEmail(registerRequest.getEmail()));
        Optional<Account> existingUsernameAccount = Optional.ofNullable(iAccountRepository.findByUsername(registerRequest.getUsername()));

        // Nếu email hoặc số điện thoại đã tồn tại, trả về mã lỗi BAD_REQUEST
        if (existingEmailAccount.isPresent() || existingUsernameAccount.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Nếu không có tài khoản nào sử dụng email hoặc số điện thoại này, tiến hành đăng ký
        Account registeredAccount = iAccountRepository.save(registerRequest);
        return new ResponseEntity<>(registeredAccount, HttpStatus.OK);
    }

}