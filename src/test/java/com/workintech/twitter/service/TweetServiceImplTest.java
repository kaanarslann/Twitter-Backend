package com.workintech.twitter.service;

import com.workintech.twitter.dto.TweetPatchRequestDto;
import com.workintech.twitter.dto.TweetRequestDto;
import com.workintech.twitter.dto.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.TweetUserIdNotMatchedException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.TweetMapper;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ExtendWith(MockitoExtension.class)
class TweetServiceImplTest {

    private TweetService tweetService;

    @Mock
    private TweetRepository tweetRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private TweetMapper tweetMapper;

    private Tweet tweet;
    private User user;
    private TweetResponseDto responseDto;
    private TweetRequestDto requestDto;

    @BeforeEach
    void setUp() {
        tweetService = new TweetServiceImpl(tweetRepository, userRepository, tweetMapper);

        tweet = new Tweet();
        tweet.setId(1L);
        tweet.setPost("Test");
        tweet.setCreatedAt(LocalDateTime.now());

        user = new User();
        user.setId(1L);
        user.setFullName("User");
        user.setNickName("UserNick");
        user.setEmail("user@user.com");
        user.setPassword("Test12345");
        user.setCreatedAt(LocalDate.now());
        tweet.setUser(user);

        requestDto = new TweetRequestDto("Test", 1L);
        responseDto = new TweetResponseDto(tweet.getId(), tweet.getPost(), 0, tweet.getCreatedAt(), null, user.getId(), user.getNickName());
    }

    @DisplayName("Can find tweet by id")
    @Test
    void findById() {
        Mockito.when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        Mockito.when(tweetMapper.toResponseDto(tweet)).thenReturn(responseDto);

        TweetResponseDto actualResponseDto = tweetService.findById(1L);

        assertNotNull(actualResponseDto);
        assertEquals(responseDto.post(), actualResponseDto.post());
    }

    @DisplayName("Can save tweet with userId")
    @Test
    void save() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Mockito.when(tweetMapper.toEntity(requestDto)).thenReturn(tweet);
        Mockito.when(tweetRepository.save(tweet)).thenReturn(tweet);
        Mockito.when(tweetMapper.toResponseDto(tweet)).thenReturn(responseDto);

        TweetResponseDto actualResponseDto = tweetService.save(requestDto);

        assertNotNull(actualResponseDto);
        assertEquals("Test", actualResponseDto.post());
        assertEquals(user.getId(), actualResponseDto.userId());
    }

    @DisplayName("Can throw exception when user not found")
    @Test
    void saveFail() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> {
            tweetService.save(requestDto);
        });
    }

    @DisplayName("Can update tweet with userId")
    @Test
    void update() {
        TweetPatchRequestDto tweetPatchRequestDto = new TweetPatchRequestDto("Update", 1L);
        Tweet updatedTweet = new Tweet();
        updatedTweet.setPost("Update");
        updatedTweet.setUser(user);
        updatedTweet.setCreatedAt(LocalDateTime.now());
        updatedTweet.setUpdatedAt(LocalDateTime.now());

        Mockito.when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        Mockito.when(tweetMapper.updateEntity(tweet, tweetPatchRequestDto)).thenReturn(updatedTweet);
        Mockito.when(tweetRepository.save(updatedTweet)).thenReturn(updatedTweet);
        Mockito.when(tweetMapper.toResponseDto(updatedTweet)).thenReturn(responseDto);

        TweetResponseDto actualPatchRequestDto = tweetService.update(1L, tweetPatchRequestDto);

        assertNotNull(actualPatchRequestDto);
        assertEquals("Test", actualPatchRequestDto.post());
    }

    @DisplayName("Can delete tweet")
    @Test
    void delete() {
        Mockito.when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));
        tweetService.delete(1L, user.getId());

        Mockito.verify(tweetRepository).deleteById(1L);
    }
    @DisplayName("Can throw exception when userId does not match")
    void deleteFail() {
        Mockito.when(tweetRepository.findById(1L)).thenReturn(Optional.of(tweet));

        assertThrows(TweetUserIdNotMatchedException.class, () -> {
            tweetService.delete(1L, 90L);
        });

        Mockito.verify(tweetRepository, Mockito.never()).deleteById(Mockito.anyLong());
    }
}