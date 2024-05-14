package com.example.facebook_be.service;

import com.example.facebook_be.model.Account;

public interface IAccountService extends IGeneralService<Account>{
    Account login(String username, String password);
}
