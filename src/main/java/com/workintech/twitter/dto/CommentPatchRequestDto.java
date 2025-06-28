package com.workintech.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CommentPatchRequestDto(
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(max = 280)
        String comment,
        Long userId
) {
}
