package com.workintech.twitter.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;

public record RegisterRequestDto(
        @NotNull
        @NotBlank
        @NotEmpty
        @Email
        String email,
        @NotNull
        @NotBlank
        @NotEmpty
        String password,
        @NotBlank
        @NotEmpty
        @NotNull
        String nickName,
        @NotBlank
        @NotEmpty
        @NotNull
        String fullName
) {
}
