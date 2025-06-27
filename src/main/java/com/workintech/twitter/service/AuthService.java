package com.workintech.twitter.service;

import com.workintech.twitter.dto.RegisterRequestDto;
import com.workintech.twitter.dto.RegisterResponseDto;

public interface AuthService {
    RegisterResponseDto register(RegisterRequestDto requestDto);
}
