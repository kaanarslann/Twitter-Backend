package com.workintech.twitter.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;

public record TweetRequestDto(
        @NotNull
        @NotEmpty
        @NotBlank
        @Size(max = 280)
        String post,
        Long userId
) {
}
