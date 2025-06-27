package com.workintech.twitter.mapper;

import com.workintech.twitter.dto.RetweetRequestDto;
import com.workintech.twitter.dto.RetweetResponseDto;
import com.workintech.twitter.entity.Retweet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RetweetMapper {
    public Retweet toEntity(RetweetRequestDto retweetRequestDto) {
        Retweet retweet = new Retweet();
        if(retweetRequestDto.comment() != null) {
            retweet.setComment(retweetRequestDto.comment());
        }
        retweet.setCreatedAt(LocalDateTime.now());
        return retweet;
    }

    public RetweetResponseDto toResponseDto(Retweet retweet) {
        return new RetweetResponseDto(retweet.getComment(), retweet.getTweet().getPost(), retweet.getUser().getNickName(), retweet.getCreatedAt());
    }
}
