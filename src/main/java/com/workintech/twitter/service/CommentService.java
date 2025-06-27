package com.workintech.twitter.service;

import com.workintech.twitter.dto.CommentPatchRequestDto;
import com.workintech.twitter.dto.CommentRequestDto;
import com.workintech.twitter.dto.CommentResponseDto;

public interface CommentService {
    CommentResponseDto save(CommentRequestDto commentRequestDto);
    CommentResponseDto update(Long id, CommentPatchRequestDto commentPatchRequestDto);
    void delete(Long id, Long userId);
}
