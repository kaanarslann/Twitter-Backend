package com.workintech.twitter.mapper;

import com.workintech.twitter.dto.TweetPatchRequestDto;
import com.workintech.twitter.dto.TweetRequestDto;
import com.workintech.twitter.dto.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class TweetMapper {

    public Tweet toEntity(TweetRequestDto requestDto) {
        Tweet tweet = new Tweet();
        tweet.setPost(requestDto.post());
        tweet.setLikeCount(0);
        tweet.setCreatedAt(LocalDateTime.now());
        return tweet;
    }

    public TweetResponseDto toResponseDto(Tweet tweet) {
        return new TweetResponseDto(tweet.getId(), tweet.getPost(), tweet.getLikeCount(), tweet.getCreatedAt(), tweet.getUpdatedAt(), tweet.getUser().getId(), tweet.getUser().getNickName());
    }

    public Tweet updateEntity(Tweet tweetToUpdate, TweetPatchRequestDto patchRequestDto) {
        if(patchRequestDto.post() != null) {
            tweetToUpdate.setPost(patchRequestDto.post());
            tweetToUpdate.setUpdatedAt(LocalDateTime.now());
        }
        return tweetToUpdate;
    }
}
