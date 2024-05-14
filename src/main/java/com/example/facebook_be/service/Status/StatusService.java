package com.example.facebook_be.service.Status;

import com.example.facebook_be.model.Post;
import com.example.facebook_be.repository.IStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatusService implements IStatusService {
    @Autowired
    private IStatusRepository iStatusRepository;

    @Override
    public Iterable<Post> findAll() {
        return iStatusRepository.findAll();
    }

    @Override
    public Optional<Post> findById(Long id) {
        return iStatusRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return iStatusRepository.save(post);
    }

    @Override
    public void remove(Long id) {
        iStatusRepository.deleteById(id);
    }
}
