package com.workintech.twitter.service;

import com.workintech.twitter.dto.TweetPatchRequestDto;
import com.workintech.twitter.dto.TweetRequestDto;
import com.workintech.twitter.dto.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;

import java.util.List;

public interface TweetService {
    TweetResponseDto findById(Long id);
    List<TweetResponseDto> findByUserId(Long userId);
    TweetResponseDto save(TweetRequestDto tweet);
    TweetResponseDto update(Long id, TweetPatchRequestDto tweet);
    void delete(Long id, Long userId);

}
