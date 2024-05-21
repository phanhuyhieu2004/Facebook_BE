package com.example.facebook_be.service;

import com.example.facebook_be.dto.PostForm;
import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Post;

import java.io.IOException;

public interface IPostService extends IGeneralService<Post>{

    Post savePost(PostForm postForm) throws IOException;
}
