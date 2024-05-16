package com.example.facebook_be.controller.APIStatus;

import com.example.facebook_be.model.Post;
import com.example.facebook_be.model.Visibility;
import com.example.facebook_be.service.status.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("/category/{categoryId}")
    public List<Post> getProductsByCategory(@PathVariable Long visibilityID) {
        Visibility visibility = new Visibility();
        visibility.setVisibility_id(visibilityID);
        return postService.getPostsByVisibility(visibility);
    }

        // Lấy tất cả các trạng thái (posts)
        @GetMapping
        public ResponseEntity<Iterable<Post>> getAllStatuses() {
            Iterable<Post> posts = postService.findAll();
            return new ResponseEntity<>(posts, HttpStatus.OK);
        }
        // Lấy thông tin của một trạng thái cụ thể theo ID
        @GetMapping("/{id}")
        public ResponseEntity<Post> getStatusById(@PathVariable Long id) {
            Optional<Post> post = postService.findById(id);
            return post.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                    .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        // Tạo một trạng thái mới
        @PostMapping
        public ResponseEntity<Post> createStatus(@RequestBody Post post) {
            Post savedPost = postService.save(post);
            return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
        }
        // Cập nhật một trạng thái cụ thể theo ID
        @PutMapping("/{id}")
        public ResponseEntity<Post> updateStatus(@PathVariable Long id, @RequestBody Post postDetails) {
            Optional<Post> post = postService.findById(id);
            if (post.isPresent()) {
                Post existingPost = post.get();
                existingPost.setContext(postDetails.getContext());
                existingPost.setImage(postDetails.getImage());
                Post updatedPost = postService.save(existingPost);
                return new ResponseEntity<>(updatedPost, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
        // Xóa một trạng thái cụ thể theo ID
        @DeleteMapping("/{id}")
        public ResponseEntity<HttpStatus> deleteStatus(@PathVariable Long id) {
            Optional<Post> post = postService.findById(id);
            if (post.isPresent()) {
                postService.remove(id);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }
}
