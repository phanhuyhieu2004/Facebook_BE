package com.example.facebook_be.service;

import com.example.facebook_be.dto.PostForm;
import com.example.facebook_be.model.Post;
import com.example.facebook_be.repository.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService{
    @Value("${file-upload}")
    private String fileUpload;
    @Autowired
    private IPostRepository iPostRepository;
    @Override
    @Query(value = "SELECT * FROM post WHERE create_at ORDER BY DESC",nativeQuery = true)
    public Iterable<Post> findAll() {
        return iPostRepository.findAll() ;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return iPostRepository.findById(id);
    }

    @Override
    public Post save(Post post) {
        return iPostRepository.save(post);
    }

    @Override
    public void remove(Long id) {
iPostRepository.deleteById(id);
    }
    public Iterable<Post> findByContent(String content){
        return iPostRepository.findByContentContaining(content);
    }

    @Override
    public Post savePost(PostForm postForm) throws IOException {

        MultipartFile multipartFile = postForm.getImage();
        String fileName = multipartFile.getOriginalFilename();
        FileCopyUtils.copy(postForm.getImage().getBytes(), new File(fileUpload + fileName));

        LocalDateTime localDateTime = LocalDateTime.now();
        Post post = new Post(
                null,
                postForm.getContent(),
                fileName,

                postForm.getLikes(),
                localDateTime,

                postForm.getAccount(),

                postForm.getVisibility()
        );
        return iPostRepository.save(post);
    }


@Override
    public int getTotalLikes() {
        List<Post> posts = iPostRepository.findAll();
        int totalLikes = 0;
        for (Post post : posts) {
            totalLikes += post.getLikes();
        }
        return totalLikes;
    }
}
