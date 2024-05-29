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

@RestController
@RequestMapping("api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

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

    @GetMapping("")
    public ResponseEntity<Iterable<Comment>> getAllComments() {

        List<Comment> comments= (List<Comment>) commentService.findAll();

        if (comments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Comment> findById(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> saveComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        Optional<Comment> commentOptional =commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        comment.setComment_id(commentOptional.get().getComment_id());
        return new ResponseEntity<>(commentService.save(comment), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable Long id) {
        Optional<Comment> commentOptional = commentService.findById(id);
        if (!commentOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        commentService.remove(id);
        return new ResponseEntity<>(commentOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalComments() {
        Long totalCount = commentService.getTotalComments();
        return new ResponseEntity<>(totalCount, HttpStatus.OK);
    }
}
