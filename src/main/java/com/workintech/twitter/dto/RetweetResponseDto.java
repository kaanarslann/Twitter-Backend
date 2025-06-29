package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record RetweetResponseDto(Long retweetId, String comment, String orgTweetPost, String userName, LocalDateTime createdAt) {
}
