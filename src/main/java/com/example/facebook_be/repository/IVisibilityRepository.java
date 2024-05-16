package com.example.facebook_be.repository;

import com.example.facebook_be.model.Visibility;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVisibilityRepository extends JpaRepository<Visibility,Long> {
}
