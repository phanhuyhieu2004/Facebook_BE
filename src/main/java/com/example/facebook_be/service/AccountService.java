package com.example.facebook_be.service;

import com.example.facebook_be.model.Account;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountService implements IAccountService{
    @Override
    public Iterable<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
