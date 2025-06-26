package com.workintech.twitter.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class LikeNotFoundException extends TwitterException{
    public LikeNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
