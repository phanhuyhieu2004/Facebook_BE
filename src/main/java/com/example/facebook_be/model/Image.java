package com.example.facebook_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Entity
@Table(name="image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long image_id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Image(Long image_id, String name, Account account, Post post) {
        this.image_id = image_id;
        this.name = name;

        this.account = account;
        this.post = post;
    }

    public Image() {
    }


}
