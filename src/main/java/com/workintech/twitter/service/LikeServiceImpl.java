package com.workintech.twitter.service;

import com.workintech.twitter.dto.LikeRequestDto;
import com.workintech.twitter.dto.LikeResponseDto;
import com.workintech.twitter.entity.Like;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.LikeNotFoundException;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.LikeMapper;
import com.workintech.twitter.repository.LikeRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService{

    @Autowired
    private final LikeRepository likeRepository;

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final LikeMapper likeMapper;


    @Override
    public LikeResponseDto like(LikeRequestDto likeRequestDto) {
        User user = userRepository.findById(likeRequestDto.userId()).orElseThrow(() -> new UserNotFoundException("User not found! Id no: " + likeRequestDto.userId()));
        Tweet tweet = tweetRepository.findById(likeRequestDto.tweetId()).orElseThrow(() -> new TweetNotFoundException("Tweet not found! Id no: " + likeRequestDto.tweetId()));
        Like like = likeMapper.toEntity(likeRequestDto);
        like.setUser(user);
        like.setTweet(tweet);
        tweet.likeTweet();
        return likeMapper.toResponseDto(likeRepository.save(like));
    }

    @Override
    public void dislike(LikeRequestDto likeRequestDto) {
        User user = userRepository.findById(likeRequestDto.userId()).orElseThrow(() -> new UserNotFoundException("User not found! Id no: " + likeRequestDto.userId()));
        Tweet tweet = tweetRepository.findById(likeRequestDto.tweetId()).orElseThrow(() -> new TweetNotFoundException("Tweet not found! Id no: " + likeRequestDto.tweetId()));
        Like like = likeRepository.findByUserAndTweet(user, tweet).orElseThrow(() -> new LikeNotFoundException("Tweet is not liked!"));

        tweet.dislikeTweet();
        tweetRepository.save(tweet);

        likeRepository.delete(like);
    }
}
