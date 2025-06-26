package com.workintech.twitter.controller;

import com.workintech.twitter.dto.CommentPatchRequestDto;
import com.workintech.twitter.dto.CommentRequestDto;
import com.workintech.twitter.dto.CommentResponseDto;
import com.workintech.twitter.service.CommentService;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    @Autowired
    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto save(@Validated @RequestBody CommentRequestDto commentRequestDto) {
        return commentService.save(commentRequestDto);
    }

    @PatchMapping("/{id}")
    public CommentResponseDto update(@Positive @PathVariable("id") Long id, @Validated @RequestBody CommentPatchRequestDto commentPatchRequestDto) {
        return commentService.update(id, commentPatchRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Positive @PathVariable("id") Long id) {
        commentService.delete(id);
    }

}
