package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record LikeResponseDto(Long likeId, LocalDateTime createdAt, Long userId, String userName, Long tweetId) {
}
