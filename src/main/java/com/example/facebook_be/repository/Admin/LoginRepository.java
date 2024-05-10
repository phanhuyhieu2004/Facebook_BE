package com.example.facebook_be.repository.Admin;


import com.example.facebook_be.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends CrudRepository<Account, Long> {
    Account findByUsernameAndPassword(String username, String password);
    Account findByEmailAndPassword(String email, String password);
    Account findByPhoneAndPassword(String phone, String password);
}