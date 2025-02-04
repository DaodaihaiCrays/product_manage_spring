package com.example.store_spring.util;

import com.example.store_spring.models.respone.RestRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException  {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestRespone<Object>> handleValidationException(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();
        for (FieldError error : exception.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage()); // Gán lỗi cho từng field
        }

        RestRespone<Object> res = new RestRespone<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("Validation failed");
        res.setMessage(errors); // Đưa danh sách lỗi vào message

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = InvalidException.class)
    public ResponseEntity<RestRespone<Object>> handleInvalidException(Exception exception) {

        RestRespone<Object> res = new RestRespone<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("Invalid request");
        res.setMessage(exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestRespone<Object>> handleException(Exception exception) {

        RestRespone<Object> res = new RestRespone<>();
        res.setStatusCode(HttpStatus.BAD_REQUEST.value());
        res.setError("Invalid request");
        res.setMessage("Somthing went wrong");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

}
