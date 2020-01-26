package com.letshadow.back.exception.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private int code;
    private String message;

    public static ErrorResponse of(int code, String message){
        return new ErrorResponse(code, message);
    }
}
