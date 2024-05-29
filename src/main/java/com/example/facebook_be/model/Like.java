package com.example.facebook_be.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="likes")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long like_id;


    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    public Like() {
    }

    public Like(Long like_id, Account account, Post post) {
        this.like_id = like_id;
        this.account = account;
        this.post = post;
    }
}
