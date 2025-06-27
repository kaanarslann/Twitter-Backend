package com.workintech.twitter.controller;

import com.workintech.twitter.dto.TweetPatchRequestDto;
import com.workintech.twitter.dto.TweetRequestDto;
import com.workintech.twitter.dto.TweetResponseDto;
import com.workintech.twitter.service.TweetService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {

    @Autowired
    private final TweetService tweetService;

    @GetMapping("/findById/{id}")
    public TweetResponseDto findById(@Positive @PathVariable("id") Long id) {
        return tweetService.findById(id);
    }

    @GetMapping("/findByUserId/{userId}")
    public List<TweetResponseDto> findByUserId(@Positive @PathVariable("userId") Long userId) {
        return tweetService.findByUserId(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TweetResponseDto save(@Validated @RequestBody TweetRequestDto tweetRequestDto) {
        return tweetService.save(tweetRequestDto);
    }

    @PatchMapping("/{id}")
    public TweetResponseDto update(@Positive @PathVariable("id") Long id, @Validated @RequestBody TweetPatchRequestDto tweetPatchRequestDto) {
        return tweetService.update(id, tweetPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable("id") Long id, @Positive @RequestParam Long userId) {
        tweetService.delete(id, userId);
    }

}
