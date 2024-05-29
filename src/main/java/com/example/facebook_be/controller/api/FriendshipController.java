package com.example.facebook_be.controller.api;

import com.example.facebook_be.model.Account;
import com.example.facebook_be.model.Friendship;
import com.example.facebook_be.repository.IAccountRepository;
import com.example.facebook_be.repository.IFriendshipRepository;
import com.example.facebook_be.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController("apiFriendController")
@RequestMapping("/api/friends")
@CrossOrigin(origins = "*")
public class FriendshipController {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private FriendshipService friendshipService;

    @GetMapping("/{userId}")
    public List<Account> getFriends(@PathVariable Long userId) {
        Account user = iAccountRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        List<Friendship> friendships = friendshipService.getFriendsByUser(user);
        return friendships.stream().map(Friendship::getObjHaveRelationships).collect(Collectors.toList());
    }

}
