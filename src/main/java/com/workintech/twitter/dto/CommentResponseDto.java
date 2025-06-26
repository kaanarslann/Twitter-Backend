package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(String comment, LocalDateTime createdAt, Long userId, String userName) {
}
