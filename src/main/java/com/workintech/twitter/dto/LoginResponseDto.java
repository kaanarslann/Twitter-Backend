package com.workintech.twitter.dto;

public record LoginResponseDto(
        String token,
        String fullName,
        String nickName
) {
}
