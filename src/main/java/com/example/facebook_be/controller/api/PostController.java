package com.example.facebook_be.controller.api;

import com.example.facebook_be.model.Post;
import com.example.facebook_be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiPostController")
@RequestMapping("api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("")
    public ResponseEntity<Iterable<Post>> getAllPosts(@RequestParam(value = "content", required = false) String content) {

        List<Post> posts;
        if (content != null && !content.isEmpty()) {
            posts = (List<Post>) postService.findByContent(content);

        } else {
            posts = (List<Post>) postService.findAll();
        }
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @PostMapping("")
    public RequestEntity<Post>createPost(){
        Possyt v
    }


}
