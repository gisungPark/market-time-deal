package com.market.timedeal.exception;

import com.market.timedeal.dto.CustomResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(DuplicatedIdException.class)
    public ResponseEntity<CustomResponseEntity> duplicateIdException (Exception e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomResponseEntity(e.getMessage()));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<CustomResponseEntity> NotFoundException (Exception e){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new CustomResponseEntity(e.getMessage()));
    }
}
