package com.workintech.twitter.controller;

import com.workintech.twitter.dto.RetweetRequestDto;
import com.workintech.twitter.dto.RetweetResponseDto;
import com.workintech.twitter.service.RetweetService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
@RequiredArgsConstructor
public class RetweetController {

    @Autowired
    private final RetweetService retweetService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RetweetResponseDto save(@Validated @RequestBody RetweetRequestDto retweetRequestDto) {
        return retweetService.save(retweetRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable("id") Long id, @Positive @RequestParam Long userId) {
        retweetService.delete(id, userId);
    }
}
