package com.example.facebook_be.service;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Comment;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.repository.IAccountRepository;
import com.example.facebook_be.repository.ICommentRepository;
import com.example.facebook_be.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
@Service
public class CommentService implements ICommentService {

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Autowired
    private ICommentRepository commentRepository;
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Iterable<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Optional<Comment> findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment save(Comment comment) {
        return null;
    }

    // LƯU COMMENT
    @Override
    public Comment save(Comment comment, MultipartFile imageFile) throws IOException {
        // Lấy thông tin bài viết và người dùng từ comment
        Long postId = comment.getPost().getPost_id();
        Long userId = comment.getAccount().getAccount_id();

        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Account user = accountRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        // Kiểm tra quyền comment nếu bài viết có trạng thái "Friends"
        if ("Friends".equals(post.getVisibility().getName())) {
            boolean isFriend = post.getAccount().getFriends().contains(user);
            if (!isFriend) {
                throw new RuntimeException("You are not allowed to comment on this post");
            }
        }

        // Xử lý file ảnh
        if ( imageFile!= null && !imageFile.isEmpty()) {
            String originalFilename = imageFile.getOriginalFilename();
            if (originalFilename != null) {
                String newFileName = System.currentTimeMillis() + "_" + originalFilename;
                Files.copy(imageFile.getInputStream(), Paths.get(uploadDir + File.separator + newFileName));
                comment.setImage(newFileName);  // Lưu toàn bộ tên file bao gồm cả tên và đuôi file
            }
        }

        // Gán bài viết và người dùng đã xác nhận vào comment
        comment.setPost(post);
        comment.setAccount(user);
        return commentRepository.save(comment);
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }


}
