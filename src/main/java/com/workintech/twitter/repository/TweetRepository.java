package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
    @Query("SELECT t FROM Twitter t WHERE t.user = :userId")
    List<Tweet> findByUserId(Long userId);
}
