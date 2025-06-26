package com.workintech.twitter.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentRequestDto(
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(max = 280)
        String comment,
        Long tweetId,
        Long userId
) {
}
