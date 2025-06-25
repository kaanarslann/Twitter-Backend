package com.workintech.twitter.service;

import com.workintech.twitter.entity.Tweet;

import java.util.List;

public interface TweetService {
    Tweet save(Tweet tweet, Long userId);
    Tweet findById(Long id);
    List<Tweet> findByUserId(Long userId);
    Tweet update(Long id, Tweet tweet);
    void delete(Long id);

}
