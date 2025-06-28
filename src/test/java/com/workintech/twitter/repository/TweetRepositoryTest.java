package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class TweetRepositoryTest {

    @Autowired
    private TweetRepository tweetRepository;
    @Autowired
    private UserRepository userRepository;

    @DisplayName("Can find tweets by userId")
    @Test
    void findByUserId() {
        User user = new User();
        user.setFullName("User");
        user.setNickName("UserNick");
        user.setEmail("user@user.com");
        user.setPassword("Test12345");
        user.setCreatedAt(LocalDate.now());
        userRepository.save(user);

        Tweet tweet1 = new Tweet();
        tweet1.setPost("Tweet no 1");
        tweet1.setUser(user);
        tweet1.setCreatedAt(LocalDateTime.now());
        tweetRepository.save(tweet1);

        Tweet tweet2 = new Tweet();
        tweet2.setPost("Tweet no 2");
        tweet2.setUser(user);
        tweet2.setCreatedAt(LocalDateTime.now());
        tweetRepository.save(tweet2);

        List<Tweet> tweets = tweetRepository.findByUserId(user.getId());

        assertNotNull(tweets);
        assertEquals(2, tweets.size());
        assertTrue(tweets.stream().anyMatch(tweet -> tweet.getPost().equals("Tweet no 1")));
        assertTrue(tweets.stream().anyMatch(tweet -> tweet.getPost().equals("Tweet no 2")));
    }
}