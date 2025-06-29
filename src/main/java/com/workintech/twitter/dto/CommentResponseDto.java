package com.workintech.twitter.dto;

import java.time.LocalDateTime;

public record CommentResponseDto(Long commentId, String comment, LocalDateTime createdAt, Long userId, String userName) {
}
