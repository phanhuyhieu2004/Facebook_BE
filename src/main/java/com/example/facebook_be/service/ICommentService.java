package com.example.facebook_be.service;

import com.example.facebook_be.model.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ICommentService extends IGeneralService<Comment> {
    Comment save(Comment comment, MultipartFile imageFile) throws IOException;
}
