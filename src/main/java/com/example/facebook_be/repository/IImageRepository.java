package com.example.facebook_be.repository;

import com.example.facebook_be.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image,Long> {
    @Query(value = "SELECT * FROM image WHERE post_id=? ",nativeQuery = true)
public Iterable<Image> findByPostId(Long post_id);
}
