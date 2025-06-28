package com.workintech.twitter.service;

import com.workintech.twitter.dto.LoginRequestDto;
import com.workintech.twitter.dto.LoginResponseDto;
import com.workintech.twitter.dto.RegisterRequestDto;
import com.workintech.twitter.dto.RegisterResponseDto;

public interface AuthService {
    RegisterResponseDto register(RegisterRequestDto registerRequestDto);
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
