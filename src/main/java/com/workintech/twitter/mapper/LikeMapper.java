package com.workintech.twitter.mapper;

import com.workintech.twitter.dto.LikeRequestDto;
import com.workintech.twitter.dto.LikeResponseDto;
import com.workintech.twitter.entity.Like;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class LikeMapper {
    public Like toEntity(LikeRequestDto requestDto) {
        Like like = new Like();
        like.setLikedAt(LocalDateTime.now());
        return like;
    }

    public LikeResponseDto toResponseDto(Like like) {
        return new LikeResponseDto(like.getLikedAt(), like.getUser().getId(), like.getUser().getNickName(), like.getTweet().getId());
    }
}
