package com.example.facebook_be.service.status;

import com.example.facebook_be.model.Visibility;
import com.example.facebook_be.repository.status.IVisibilityRepository;
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
        return iVisibilityRepository.findById(id);
    }

    @Override
    public Visibility save(Visibility visibility) {
        return iVisibilityRepository.save(visibility);
    }

    @Override
    public void remove(Long id) {
        iVisibilityRepository.deleteById(id);
    }
}
