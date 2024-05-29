package com.example.facebook_be.controller.api;

import com.example.facebook_be.dto.LikeRequestDTO;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Like;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.repository.IAccountRepository;
import com.example.facebook_be.repository.ILikeRepository;
import com.example.facebook_be.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController("apiLikeController")

@CrossOrigin(origins = "*")
public class LikeController {
    @Autowired
    private ILikeRepository likeRepository;
    @Autowired
    private IPostRepository iPostRepository;
    @Autowired
    private IAccountRepository iAccountRepository;
    @GetMapping("/posts/{postId}/likes/{accountId}")
    public ResponseEntity<?> checkIfAccountLikedPost(@PathVariable Long postId, @PathVariable Long accountId) {
        Optional<Like> like = likeRepository.findByPostAndAccount(new Post(postId), new Account(accountId));
        if (like.isPresent()) {
            return ResponseEntity.ok("Account liked the post.");
        } else {
            return ResponseEntity.ok("Account did not like the post.");
        }
    }
    @PostMapping("/posts/{postId}/likes")
    public ResponseEntity<?> addLikeToPost(@PathVariable Long postId, @RequestBody LikeRequestDTO likeRequestDTO) {
        Long accountId = likeRequestDTO.getAccountId();
        try {
            Optional<Post> postOptional = iPostRepository.findById(postId);
            Optional<Account> accountOptional = iAccountRepository.findById(accountId);

            if (postOptional.isPresent() && accountOptional.isPresent()) {
                Post post = postOptional.get();
                Account account = accountOptional.get();

                Like like = new Like();
                like.setPost(post);
                like.setAccount(account);
                likeRepository.save(like);

                post.setLikes(post.getLikes() + 1);
                iPostRepository.save(post);

                return ResponseEntity.ok("Like added successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add like");
        }
    }
    @DeleteMapping("/posts/{postId}/likes/{accountId}")
    public ResponseEntity<?> removeLikeFromPost(@PathVariable Long postId, @PathVariable Long accountId) {
        try {
            Optional<Post> postOptional = iPostRepository.findById(postId);
            Optional<Account> accountOptional = iAccountRepository.findById(accountId);

            if (postOptional.isPresent() && accountOptional.isPresent()) {
                Post post = postOptional.get();
                Account account = accountOptional.get();

                Optional<Like> likeOptional = likeRepository.findByPostAndAccount(post, account);

                if (likeOptional.isPresent()) {
                    likeRepository.delete(likeOptional.get());
                    post.setLikes(post.getLikes() - 1);
                    iPostRepository.save(post);

                    return ResponseEntity.ok("Like removed successfully");
                } else {
                    return ResponseEntity.notFound().build();
                }
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove like");
        }
    }

}
