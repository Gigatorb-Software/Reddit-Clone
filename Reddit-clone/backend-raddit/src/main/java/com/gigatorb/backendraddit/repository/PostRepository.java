package com.gigatorb.backendraddit.repository;

import com.gigatorb.backendraddit.model.Post;
import com.gigatorb.backendraddit.model.Subreddit;
import com.gigatorb.backendraddit.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);


    Post findByPostId(Long postId);
}
