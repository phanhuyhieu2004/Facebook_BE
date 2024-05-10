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

}

