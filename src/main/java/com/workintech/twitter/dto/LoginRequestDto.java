package com.workintech.twitter.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDto(
        @NotBlank
        @NotNull
        @NotEmpty
        @Email
        String email,
        @NotBlank
        @NotNull
        @NotEmpty
        String password
) {
}
