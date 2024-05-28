package com.example.facebook_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="post")
@Data

@EntityListeners(AuditingEntityListener.class)

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long post_id;
    private String content;


    private int likes ;
    @CreatedDate

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;
    @OneToOne

    @JoinColumn(name = "visibility_id")
    private Visibility visibility;

    public Post(Long post_id, String content,  int likes , LocalDateTime createdAt, Account account, Visibility visibility) {
        this.post_id = post_id;
        this.content = content;


        this.likes  = likes ;
        this.createdAt = createdAt;
        this.account = account;
        this.visibility = visibility;
    }

    public Post() {
    }

    public Post(Long post_id) {
        this.post_id = post_id;
    }
}
