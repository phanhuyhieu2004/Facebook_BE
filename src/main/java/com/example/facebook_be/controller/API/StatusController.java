package com.example.facebook_be.controller.API;

import com.example.facebook_be.model.Post;
import com.example.facebook_be.service.Status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    private StatusService statusService;

    // Lấy tất cả các trạng thái (posts)
    @GetMapping
    public ResponseEntity<Iterable<Post>> getAllStatuses() {
        Iterable<Post> posts = statusService.findAll();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }
    // Lấy thông tin của một trạng thái cụ thể theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getStatusById(@PathVariable Long id) {
        Optional<Post> post = statusService.findById(id);
        return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Tạo một trạng thái mới
    @PostMapping
    public ResponseEntity<Post> createStatus(@RequestBody Post post) {
        Post savedPost = statusService.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
    }
    // Cập nhật một trạng thái cụ thể theo ID
    @PutMapping("/{id}")
    public ResponseEntity<Post> updateStatus(@PathVariable Long id, @RequestBody Post postDetails) {
        Optional<Post> post = statusService.findById(id);
        if (post.isPresent()) {
            Post existingPost = post.get();
            existingPost.setContext(postDetails.getContext());
            existingPost.setImage(postDetails.getImage());
            Post updatedPost = statusService.save(existingPost);
            return new ResponseEntity<>(updatedPost, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Xóa một trạng thái cụ thể theo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStatus(@PathVariable Long id) {
        Optional<Post> post = statusService.findById(id);
        if (post.isPresent()) {
            statusService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
