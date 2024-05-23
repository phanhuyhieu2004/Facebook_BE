package com.example.facebook_be.controller.api;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Comment;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController("apiCommentController")
@RequestMapping("api/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

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
    public ResponseEntity<Comment> saveComment(@RequestParam ("content") String content,
                                                                                               @RequestParam("account_id") Long accountId,
                                                                                               @RequestParam("post_id") Long postId,
                                                                                               @RequestParam(value = "image", required = false) MultipartFile image) {
        Comment comment = new Comment();
        comment.setContent(content);
        Account account = new Account();
        account.setAccount_id(accountId);
        comment.setAccount(account);
        Post post = new Post();
        post.setPost_id(postId);
        comment.setPost(post);

        try {
            Comment createdComment = commentService.save(comment, image);
            return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

}
