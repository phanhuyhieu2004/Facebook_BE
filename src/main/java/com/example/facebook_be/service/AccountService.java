package com.example.facebook_be.service;

import com.example.facebook_be.dto.AccountRequest;
import com.example.facebook_be.dto.AccountSpec;
import com.example.facebook_be.dto.PaginateRequest;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.repository.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AccountService implements IAccountService{
    @Autowired
    private IAccountRepository iAccountRepository;
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

    @Override
    public Account login(String identifier, String password) {
        if (identifier.contains("@")) {
            return iAccountRepository.findByEmailAndPassword(identifier, password);
        } else {
            return iAccountRepository.findByPhoneAndPassword(identifier, password);
        }
    }

    public void blockAccount(Long accountId) {
        Optional<Account> accountOptional = iAccountRepository.findById(accountId);
        if (accountOptional.isPresent()) {
            Account account = accountOptional.get();
            account.setStatus("block");
            iAccountRepository.save(account);
        } else {
            throw new RuntimeException("Account not found");
        }
    }
    public Page<Account> findAll(AccountRequest accountRequest, PaginateRequest paginateRequest) {
        Specification<Account> spec = new AccountSpec(accountRequest);

        return iAccountRepository.findAll(spec, PageRequest.of(paginateRequest.getPage(), paginateRequest.getSize()));
    }


}
