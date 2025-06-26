package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record LikeResponseDto(LocalDateTime createdAt, Long userId, String userName, Long tweetId) {
}
