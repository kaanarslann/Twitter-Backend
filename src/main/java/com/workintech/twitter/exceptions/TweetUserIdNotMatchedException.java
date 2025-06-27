package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class TweetUserIdNotMatchedException extends TwitterException{
    public TweetUserIdNotMatchedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}
