package com.workintech.twitter.service;

import com.workintech.twitter.dto.RetweetRequestDto;
import com.workintech.twitter.dto.RetweetResponseDto;

public interface RetweetService {
    RetweetResponseDto save(RetweetRequestDto retweetRequestDto);
    void delete(Long id, Long userId);
}
