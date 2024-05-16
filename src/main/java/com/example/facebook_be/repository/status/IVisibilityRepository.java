package com.example.facebook_be.repository.status;

import com.example.facebook_be.model.Visibility;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVisibilityRepository extends CrudRepository<Visibility,Long> {
}
