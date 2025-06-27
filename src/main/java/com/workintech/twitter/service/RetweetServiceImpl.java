package com.workintech.twitter.service;

import com.workintech.twitter.dto.RetweetRequestDto;
import com.workintech.twitter.dto.RetweetResponseDto;
import com.workintech.twitter.entity.Retweet;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.RetweetNotFoundException;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.TweetUserIdNotMatchedException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.RetweetMapper;
import com.workintech.twitter.repository.RetweetRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RetweetServiceImpl implements RetweetService{

    @Autowired
    private RetweetRepository retweetRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final RetweetMapper retweetMapper;

    @Override
    public RetweetResponseDto save(RetweetRequestDto retweetRequestDto) {
        User user = userRepository.findById(retweetRequestDto.userId()).orElseThrow(() -> new UserNotFoundException("User not found! Id no: " + retweetRequestDto.userId()));

        Tweet tweet = tweetRepository.findById(retweetRequestDto.tweetId()).orElseThrow(() -> new TweetNotFoundException("Tweet not found! Id no: " + retweetRequestDto.tweetId()));

        Retweet retweet = retweetMapper.toEntity(retweetRequestDto);
        retweet.setUser(user);
        retweet.setTweet(tweet);

        return retweetMapper.toResponseDto(retweetRepository.save(retweet));
    }

    @Override
    public void delete(Long id, Long userId) {
        Retweet retweet = retweetRepository.findById(id).orElseThrow(() -> new RetweetNotFoundException("Retweet not found! Id no: " + id));
        if(!userId.equals(retweet.getUser().getId())) {
            throw new TweetUserIdNotMatchedException("Retweet User Id does not match!");
        }
        retweetRepository.deleteById(id);
    }
}
