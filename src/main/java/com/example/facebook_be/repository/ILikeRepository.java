package com.example.facebook_be.repository;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Like;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.service.IGeneralService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ILikeRepository  extends JpaRepository<Like,Long> {
    Optional<Like> findByPostAndAccount(Post post, Account account);
}
