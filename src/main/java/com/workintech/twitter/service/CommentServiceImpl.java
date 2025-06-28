package com.workintech.twitter.service;


import com.workintech.twitter.dto.CommentPatchRequestDto;
import com.workintech.twitter.dto.CommentRequestDto;
import com.workintech.twitter.dto.CommentResponseDto;
import com.workintech.twitter.entity.Comment;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.CommentNotFoundException;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.TweetUserIdNotMatchedException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.CommentMapper;
import com.workintech.twitter.repository.CommentRepository;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    @Autowired
    private final CommentRepository commentRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final CommentMapper commentMapper;

    @Override
    public CommentResponseDto save(CommentRequestDto commentRequestDto) {
        User user = userRepository.findById(commentRequestDto.userId()).orElseThrow(() -> new UserNotFoundException("User not found! Id no: " + commentRequestDto.userId()));

        Tweet tweet = tweetRepository.findById(commentRequestDto.tweetId()).orElseThrow(() -> new TweetNotFoundException("Tweet not found! Id no: " + commentRequestDto.tweetId()));

        Comment comment = commentMapper.toEntity(commentRequestDto);
        comment.setTweet(tweet);
        comment.setUser(user);

        return commentMapper.toResponseDto(commentRepository.save(comment));
    }

    @Override
    public CommentResponseDto update(Long id, CommentPatchRequestDto commentPatchRequestDto) {
        Comment commentToUpdate = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found! Id no: " + id));
        Long commentOwnerId = commentToUpdate.getUser().getId();
        if(!commentOwnerId.equals(commentPatchRequestDto.userId())) {
            throw new TweetUserIdNotMatchedException("Comment User Id does not match!");
        }

        commentToUpdate = commentMapper.updateEntity(commentToUpdate, commentPatchRequestDto);

        return commentMapper.toResponseDto(commentRepository.save(commentToUpdate));
    }

    @Override
    public void delete(Long id, Long userId) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new CommentNotFoundException("Comment not found! Id no: " + id));
        Long commentOwnerId = comment.getUser().getId();
        Long tweetOwnerId = comment.getTweet().getUser().getId();
        if(userId.equals(commentOwnerId) || userId.equals(tweetOwnerId)) {
            commentRepository.deleteById(id);
        } else {
            throw new TweetUserIdNotMatchedException("Tweet User Id or Comment User Id does not match!");
        }
    }
}
