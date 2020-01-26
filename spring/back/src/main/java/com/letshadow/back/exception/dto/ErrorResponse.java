package com.letshadow.back.exception.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private int code;
    private String message;

    public static ErrorResponse of(int code, String message){
        return new ErrorResponse(code, message);
    }

    public static ErrorResponse of(int code, FieldError fieldError) {
        if(fieldError == null){
            return new ErrorResponse(code, "invalid params");
        } else {
            return new ErrorResponse(code, fieldError.getDefaultMessage());
        }
    }
}