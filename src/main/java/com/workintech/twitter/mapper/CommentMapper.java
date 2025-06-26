package com.workintech.twitter.mapper;

import com.workintech.twitter.dto.CommentPatchRequestDto;
import com.workintech.twitter.dto.CommentRequestDto;
import com.workintech.twitter.dto.CommentResponseDto;
import com.workintech.twitter.entity.Comment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentMapper {
    public Comment toEntity(CommentRequestDto requestDto) {
        Comment comment = new Comment();
        comment.setComment(requestDto.comment());
        comment.setCreatedAt(LocalDateTime.now());
        return comment;
    }

    public CommentResponseDto toResponseDto(Comment comment) {
        return new CommentResponseDto(comment.getComment(), comment.getCreatedAt(), comment.getUser().getId(), comment.getUser().getUserName());
    }

    public Comment updateEntity(Comment commentToUpdate, CommentPatchRequestDto patchRequestDto) {
        if(patchRequestDto.comment() != null) {
            commentToUpdate.setComment(patchRequestDto.comment());
            commentToUpdate.setUpdatedAt(LocalDateTime.now());
        }
        return commentToUpdate;
    }
}
