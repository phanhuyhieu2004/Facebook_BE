package com.example.facebook_be.dto;

import lombok.Data;

@Data
public class AccountRequest {
    private String username;

    private String birthday;

    public AccountRequest() {
    }

    public AccountRequest(String username, String birthday) {
        this.username = username;
        this.birthday = birthday;
    }
}
