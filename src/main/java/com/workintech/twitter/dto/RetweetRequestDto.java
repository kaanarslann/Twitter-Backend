package com.workintech.twitter.dto;

import jakarta.validation.constraints.Size;

public record RetweetRequestDto(
        @Size(max = 280)
        String comment,
        Long userId,
        Long tweetId
) {
}
