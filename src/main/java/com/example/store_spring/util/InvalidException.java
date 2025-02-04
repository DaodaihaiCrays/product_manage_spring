package com.example.store_spring.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidException extends RuntimeException{

    public InvalidException(String msg) {
        super(msg);
    }
}
