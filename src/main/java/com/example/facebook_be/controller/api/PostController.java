package com.example.facebook_be.controller.api;

import com.example.facebook_be.dto.PostForm;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.repository.IPostRepository;
import com.example.facebook_be.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController("apiPostController")
@RequestMapping("api/posts")
@CrossOrigin(origins = "*")
public class PostController {
    @Autowired
    private PostService postService;
@Autowired
private IPostRepository iPostRepository;
    @GetMapping("")
    public ResponseEntity<Iterable<Post>> getAllPosts() {

        List<Post> posts= (List<Post>) iPostRepository.findByCreatedAtOrderBy();

        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> savePost(@ModelAttribute PostForm postForm) throws IOException {
        return new ResponseEntity<>(postService.savePost(postForm), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional =postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setPost_id(postOptional.get().getPost_id());
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }


}
