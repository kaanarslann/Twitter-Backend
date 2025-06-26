package com.workintech.twitter.dto;

import java.time.LocalDate;

public record TweetPatchRequestDto(String post, int likeCount, LocalDate createdAt, LocalDate updatedAt) {
}
