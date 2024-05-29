package com.example.facebook_be.service;

import com.example.facebook_be.model.Comment;
import com.example.facebook_be.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CommentService implements ICommentService{
    @Autowired
    private ICommentRepository iCommentRepository;

    @Override
    public Iterable<Comment> findAll() {
        return iCommentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return iCommentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return iCommentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        iCommentRepository.deleteById(id);
    }

    public Long getTotalComments() {
        return iCommentRepository.count();
    }

}
