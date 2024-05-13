package com.example.facebook_be.service.admin;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.repository.Admin.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class LoginService implements ILoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Account login(String identifier, String password) {
        if (identifier.contains("@")) {
            return loginRepository.findByEmailAndPassword(identifier, password);
        } else {
            return loginRepository.findByPhoneAndPassword(identifier, password);
        }
    }

    @Override
    public Iterable<Account> findAllByRoleNot(int role) {
        return loginRepository.findAllByRoleNot(role);
    }


    @Override
    public Iterable<Account> findAll() {
        return loginRepository.findAll();
    }

    @Override
    public Account save(Account account) {
        return loginRepository.save(account);
    }

    public boolean isUsernameExists(String username) {
        return loginRepository.existsByUsername(username);
    }

    public Account registerAccount(Account account) throws IllegalArgumentException {
        if (isUsernameExists(account.getUsername())) {
            throw new IllegalArgumentException("Tên người dùng đã tồn tại. Vui lòng chọn tên khác.");
        }
        account.setRole(2);
        account.setStatus("Active");
        return loginRepository.save(account);
    }

}

