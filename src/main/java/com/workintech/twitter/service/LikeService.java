package com.workintech.twitter.service;

import com.workintech.twitter.dto.LikeRequestDto;
import com.workintech.twitter.dto.LikeResponseDto;

public interface LikeService {
    LikeResponseDto like(LikeRequestDto likeRequestDto);
    void dislike(LikeRequestDto likeRequestDto);
}
