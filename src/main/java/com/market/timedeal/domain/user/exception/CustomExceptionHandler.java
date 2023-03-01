package com.market.timedeal.domain.user.exception;

import com.market.timedeal.domain.user.dto.CustomResponseEntity;
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

}
