package com.workintech.twitter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(TwitterException twitterException) {
        TwitterErrorResponse response = new TwitterErrorResponse(twitterException.getMessage(), twitterException.getHttpStatus().value(), System.currentTimeMillis(), LocalDateTime.now());

        return new ResponseEntity<>(response, twitterException.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<TwitterErrorResponse> handleException(Exception exception) {
        TwitterErrorResponse response = new TwitterErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value(), System.currentTimeMillis(), LocalDateTime.now());

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
