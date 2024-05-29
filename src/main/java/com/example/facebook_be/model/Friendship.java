package com.example.facebook_be.model;

import com.example.facebook_be.model.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friendships")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendshipID;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "account_id")
    private Account mainObj;

    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "account_id")
    private Account objHaveRelationships;
}