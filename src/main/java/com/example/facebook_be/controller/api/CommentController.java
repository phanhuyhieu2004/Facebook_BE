package com.example.facebook_be.controller.api;

import com.example.facebook_be.model.Comment;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.repository.ICommentRepository;
import com.example.facebook_be.repository.IPostRepository;
import com.example.facebook_be.service.CommentService;
import com.example.facebook_be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@CrossOrigin(origins = "*")
@RestController

public class CommentController {

    @Autowired
    private ICommentRepository commentRepository;


    @Autowired
    private IPostRepository postRepository;

    @GetMapping("/comments/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId) {
        return commentRepository.findByPostId(postId);
    }
    @PostMapping("/comments/{postId}")
    public Comment addCommentToPost(@PathVariable Long postId, @RequestBody Comment comment) {
        comment.setPost(postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found")));
        return commentRepository.save(comment);
    }

}
