package com.example.facebook_be.repository;

import com.example.facebook_be.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPostRepository extends JpaRepository<Post,Long> {
    public Iterable<Post>findByContentContaining(String content);
    @Query(value = "SELECT * FROM facebook.post ORDER BY created_at DESC", nativeQuery = true)
    public Iterable<Post>findByCreatedAtOrderBy();

    // Lấy các bài đăng mới nhất của bạn bè theo thứ tự ngẫu nhiên và giới hạn số lượng
    @Query(value = "SELECT * FROM facebook.post WHERE account_id IN (SELECT friend_id FROM facebook.friendships WHERE user_id = :userId) ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Post> findRandomPostsOfFriends(@Param("userId") Long userId, @Param("limit") int limit);

    // Lấy các bài đăng mới nhất của một tài khoản cụ thể theo thứ tự ngẫu nhiên và giới hạn số lượng
    @Query(value = "SELECT * FROM facebook.post WHERE account_id = :accountId ORDER BY RAND() LIMIT :limit", nativeQuery = true)
    List<Post> findRandomPostsOfSelf(@Param("accountId") Long accountId, @Param("limit") int limit);
}
