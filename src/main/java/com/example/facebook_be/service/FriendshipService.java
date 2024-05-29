package com.example.facebook_be.service;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Friendship;
import com.example.facebook_be.repository.IFriendshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FriendshipService {

    @Autowired
    private IFriendshipRepository iFriendshipRepository;

    public List<Friendship> getFriendsByUser(Account user) {
        return iFriendshipRepository.findByMainObj(user);
    }
}
