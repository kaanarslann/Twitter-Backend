package com.workintech.twitter.service;

import com.workintech.twitter.dto.TweetPatchRequestDto;
import com.workintech.twitter.dto.TweetRequestDto;
import com.workintech.twitter.dto.TweetResponseDto;
import com.workintech.twitter.entity.Tweet;
import com.workintech.twitter.entity.User;
import com.workintech.twitter.exceptions.TweetNotFoundException;
import com.workintech.twitter.exceptions.UserNotFoundException;
import com.workintech.twitter.mapper.TweetMapper;
import com.workintech.twitter.repository.TweetRepository;
import com.workintech.twitter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TweetServiceImpl implements TweetService{

    @Autowired
    private final TweetRepository tweetRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final TweetMapper tweetMapper;

    @Override
    public TweetResponseDto findById(Long id) {
        Tweet tweet = tweetRepository.findById(id).orElseThrow(() -> new TweetNotFoundException("Tweet not found! Id no: " + id));

        return tweetMapper.toResponseDto(tweet);
    }

    @Override
    public List<TweetResponseDto> findByUserId(Long userId) {
        return tweetRepository.findByUserId(userId).stream().map(tweetMapper::toResponseDto).toList();
    }

    @Override
    public TweetResponseDto save(TweetRequestDto tweetRequestDto) {
        User user = userRepository.findById(tweetRequestDto.userId()).orElseThrow(() -> new UserNotFoundException("User not found! Id no: " + tweetRequestDto.userId()));

        Tweet tweet = tweetMapper.toEntity(tweetRequestDto);
        tweet.setUser(user);

        return tweetMapper.toResponseDto(tweetRepository.save(tweet));
    }

    @Override
    public TweetResponseDto update(Long id, TweetPatchRequestDto tweetPatchRequestDto) {
        Tweet tweetToUpdate = tweetRepository.findById(id).orElseThrow(() -> new TweetNotFoundException("Tweet not found! Id no: " + id));
        tweetToUpdate = tweetMapper.updateEntity(tweetToUpdate, tweetPatchRequestDto);

        return tweetMapper.toResponseDto(tweetRepository.save(tweetToUpdate));
    }

    @Override
    public void delete(Long id) {
        tweetRepository.deleteById(id);
    }
}
