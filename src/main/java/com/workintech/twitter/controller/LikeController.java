package com.workintech.twitter.controller;

import com.workintech.twitter.dto.LikeRequestDto;
import com.workintech.twitter.dto.LikeResponseDto;
import com.workintech.twitter.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    @Autowired
    private final LikeService likeService;

    @PostMapping("/like")
    @ResponseStatus(HttpStatus.CREATED)
    public LikeResponseDto like(@Validated @RequestBody LikeRequestDto likeRequestDto) {
        return likeService.like(likeRequestDto);
    }

    @PostMapping("/dislike")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void dislike(@Validated @RequestBody LikeRequestDto likeRequestDto) {
        likeService.dislike(likeRequestDto);
    }
}
