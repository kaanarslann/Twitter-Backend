package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;

public class WrongPasswordException extends TwitterException{
    public WrongPasswordException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
