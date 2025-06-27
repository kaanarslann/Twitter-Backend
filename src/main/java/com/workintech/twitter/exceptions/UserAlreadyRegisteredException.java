package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class UserAlreadyRegisteredException extends TwitterException{
    public UserAlreadyRegisteredException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
