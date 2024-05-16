package com.example.facebook_be.repository.status;

import com.example.facebook_be.model.Post;
import com.example.facebook_be.model.Visibility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IPostRepository extends CrudRepository<Post,Long> {
    List<Post> findByVisibility(Visibility visibility);
}
