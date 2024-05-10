package com.example.facebook_be.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name="account")
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long account_id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String birthday;
    private String avatar;
    private String fullName;
    private String address;
    private String interests;
    private int friendCount;
    private int commonFriendCount;
    @Column(columnDefinition = "INT DEFAULT 1")

    private int role;
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'Active'")
    private String status;

}
