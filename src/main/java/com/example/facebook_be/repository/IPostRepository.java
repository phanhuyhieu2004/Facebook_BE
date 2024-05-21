package com.example.facebook_be.repository;

import com.example.facebook_be.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    public Iterable<Post>findByContentContaining(String content);
    @Query(value = "SELECT * FROM post ORDER BY created_at DESC", nativeQuery = true)
    public Iterable<Post>findByCreatedAtOrderBy();

}
