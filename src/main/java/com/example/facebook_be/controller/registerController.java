package com.example.facebook_be.controller;
import com.example.facebook_be.dto.AccountDTO;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class registerController {

    @Autowired
    private IAccountRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestBody AccountDTO accountDTO) {
        // Kiểm tra xem username đã tồn tại chưa
        if (userRepository.findByUsername(accountDTO.getUsername()) != null) {
            return "Username already exists";
        }
        // Kiểm tra xem email đã tồn tại chưa
        if (userRepository.findByEmail(accountDTO.getEmail()) != null) {
            return "Email already exists";
        }

        // Tạo một đối tượng User từ dữ liệu DTO
        Account user = new Account();
        user.setUsername(accountDTO.getUsername());
        user.setPassword(accountDTO.getPassword());
        user.setEmail(accountDTO.getEmail());
        user.setPhone(accountDTO.getPhone());
        user.setBirthday(accountDTO.getBirthday());
        user.setAvatar(accountDTO.getAvatar());
        user.setFullName(accountDTO.getFullName());
        user.setAddress(accountDTO.getAddress());
        user.setInterests(accountDTO.getInterests());
        user.setFriendCount(accountDTO.getFriendCount());
        user.setCommonFriendCount(accountDTO.getCommonFriendCount());
        user.setRole(accountDTO.getRole());
        user.setStatus(accountDTO.getStatus());

        // Lưu thông tin người dùng vào cơ sở dữ liệu
        userRepository.save(user);
        return "User registered successfully";
    }
}
