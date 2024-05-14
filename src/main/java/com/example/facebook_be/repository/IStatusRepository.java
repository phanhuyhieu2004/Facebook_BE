package com.example.facebook_be.repository;

import com.example.facebook_be.model.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends CrudRepository<Post , Long> {
}
