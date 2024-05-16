package com.example.facebook_be.service;

import com.example.facebook_be.model.Visibility;
import com.example.facebook_be.repository.IVisibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VisibilityService implements IVisibilityService {
@Autowired
private IVisibilityRepository iVisibilityRepository;
    @Override
    public Iterable<Visibility> findAll() {
        return iVisibilityRepository.findAll();
    }

    @Override
    public Optional<Visibility> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Visibility save(Visibility visibility) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
