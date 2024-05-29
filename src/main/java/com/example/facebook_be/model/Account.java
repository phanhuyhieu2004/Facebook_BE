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
    @Column(columnDefinition = "VARCHAR(255) DEFAULT 'active'")
    private String status;

    public Account(Long account_id, String username, String password, String email, String phone, String birthday, String avatar, String fullName, String address, String interests, int friendCount, int commonFriendCount, int role, String status) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.avatar = avatar;
        this.fullName = fullName;
        this.address = address;
        this.interests = interests;
        this.friendCount = friendCount;
        this.commonFriendCount = commonFriendCount;
        this.role = role;
        this.status = status;
    }

    public Account() {
    }

    public Account(Long account_id) {
        this.account_id = account_id;
    }
}