package com.example.facebook_be.repository;

import com.example.facebook_be.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends JpaRepository<Account,Long>, JpaSpecificationExecutor<Account> {
    Account findByEmailAndPassword(String email, String password);
    Account findByPhoneAndPassword(String phone, String password);

    Account findByEmail(String email);
    Account findByUsername(String username);


}
