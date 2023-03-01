package com.market.timedeal.domain.user.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomResponseEntity <T> {

    private String message;
    private T data;

    public CustomResponseEntity(String message) {
        this.message = message;
    }

    public CustomResponseEntity(String message, T data) {
        this.message = message;
        this.data = data;
    }

}
