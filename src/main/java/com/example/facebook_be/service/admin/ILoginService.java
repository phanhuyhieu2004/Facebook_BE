package com.example.facebook_be.service.admin;

import com.example.facebook_be.model.Account;

public interface ILoginService {
     Account login(String username, String password);
}

