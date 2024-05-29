package com.example.facebook_be.repository;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IFriendshipRepository extends JpaRepository<Friendship, Long> {
    List<Friendship> findByMainObj(Account mainObj);
}