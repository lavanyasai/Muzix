package com.stackroute.muzixapp.exceptions;

import com.stackroute.muzixapp.exceptions.TrackAlreadyExistsException;
import com.stackroute.muzixapp.exceptions.TrackNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TrackExceptionController {

        //Handle TrackAlreadyExistsException.
        @ExceptionHandler(value = TrackAlreadyExistsException.class)
        public ResponseEntity<Object> exception(TrackAlreadyExistsException exception) {
            return new ResponseEntity<>("Track already exists", HttpStatus.NOT_FOUND);
        }

        //Handle TrackNotFoundException.
        @ExceptionHandler(value = TrackNotFoundException.class)
        public ResponseEntity<Object> exception(TrackNotFoundException exception) {
            return new ResponseEntity<>("Track not found", HttpStatus.NOT_FOUND);
        }
}
