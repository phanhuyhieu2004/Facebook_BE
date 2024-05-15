//package com.example.facebook_be.service;
//
//import com.example.facebook_be.model.Post;
//import com.example.facebook_be.repository.IPostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class PostService implements IPostService{
//    @Autowired
//    private IPostRepository iPostRepository;
//    @Override
//    public Iterable<Post> findAll() {
//        return iPostRepository.findAll() ;
//    }
//
//    @Override
//    public Optional<Post> findById(Long id) {
//        return iPostRepository.findById(id);
//    }
//
//    @Override
//    public Post save(Post post) {
//        return iPostRepository.save(post);
//    }
//
//    @Override
//    public void remove(Long id) {
//iPostRepository.deleteById(id);
//    }
//    public Iterable<Post> findByContent(String content){
//        return iPostRepository.findByContentContaining(content);
//    }
//}
