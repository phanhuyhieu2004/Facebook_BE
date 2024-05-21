package com.example.facebook_be.controller.api;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.model.Visibility;
import com.example.facebook_be.repository.IAccountRepository;
import com.example.facebook_be.service.VisibilityService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
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
    @GetMapping("/api/accounts")
    public ResponseEntity<Iterable<Account>> getAllAccounts() {

        List<Account> accounts =  iAccountRepository.findAll();

        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }
    @GetMapping("/api/accounts/{id}")
    public ResponseEntity<Account> findById(@PathVariable Long id) {
        Optional<Account> accountOptional = iAccountRepository.findById(id);
        if (!accountOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(accountOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/api/login")
    public ResponseEntity<Account> login(@RequestBody Account loginRequest, HttpSession session) {
        Account account = iAccountRepository.findByUsername(loginRequest.getUsername());
        if (account == null || !account.getPassword().equals(loginRequest.getPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }if(account.getRole()!=1){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        }if(account.getStatus().equals("block")){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }else {
            // Lưu tài khoản vào session
            session.setAttribute("loggedInAccount", account);
            return new ResponseEntity<>(account, HttpStatus.OK);
        }
    }

    @PostMapping("/api/register")
    public ResponseEntity<Account> register(@RequestBody Account registerRequest) {
        // Kiểm tra sự tồn tại của email và username trong cơ sở dữ liệu
        Optional<Account> existingEmailAccount = Optional.ofNullable(iAccountRepository.findByEmail(registerRequest.getEmail()));
        Optional<Account> existingUsernameAccount = Optional.ofNullable(iAccountRepository.findByUsername(registerRequest.getUsername()));

        // Nếu email hoặc username đã tồn tại, trả về mã lỗi BAD_REQUEST
        if (existingEmailAccount.isPresent() || existingUsernameAccount.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
registerRequest.setRole(1);
registerRequest.setStatus("active");
        // Nếu không có tài khoản nào sử dụng email hoặc số điện thoại này, tiến hành đăng ký
        Account registeredAccount = iAccountRepository.save(registerRequest);
        return new ResponseEntity<>(registeredAccount, HttpStatus.OK);
    }

}