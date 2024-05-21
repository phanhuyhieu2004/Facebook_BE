package com.example.facebook_be.dto;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Visibility;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostForm {

    private String content;
    private MultipartFile image;

    private int likes ;
    private Visibility visibility;
    private Account account;
    public PostForm() {
    }

    public PostForm(String content, MultipartFile image, int likes , Visibility visibility, Account account) {
        this.content = content;
        this.image = image;

        this.likes  = likes ;
        this.visibility = visibility;
        this.account = account;
    }
}
