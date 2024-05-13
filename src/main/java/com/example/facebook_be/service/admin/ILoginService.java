package com.example.facebook_be.service.admin;

import com.example.facebook_be.model.Account;

public interface ILoginService {
     Account login(String username, String password);
    Iterable<Account> findAllByRoleNot(int role);
    Iterable<Account> findAll();
    Account save(Account account);

}

