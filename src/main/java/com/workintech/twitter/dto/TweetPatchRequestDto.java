package com.workintech.twitter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TweetPatchRequestDto(
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(max = 280)
        String post,
        Long userId
) {
}
