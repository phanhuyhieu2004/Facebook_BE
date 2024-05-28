package com.example.facebook_be.dto;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Post;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageForm {
    private MultipartFile[] images;
    private Account account;
    private Post post;

    public ImageForm(MultipartFile[] images, Account account, Post post) {
        this.images = images;
        this.account = account;
        this.post = post;
    }

    public ImageForm() {
    }
}
