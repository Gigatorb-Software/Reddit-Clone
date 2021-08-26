package com.gigatorb.backendraddit.repository;

import com.gigatorb.backendraddit.model.Post;
import com.gigatorb.backendraddit.model.User;
import com.gigatorb.backendraddit.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User currentUser);
}