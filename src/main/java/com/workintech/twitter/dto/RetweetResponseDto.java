package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record RetweetResponseDto(String comment, String orgTweetPost, String userName, LocalDateTime createdAt) {
}
