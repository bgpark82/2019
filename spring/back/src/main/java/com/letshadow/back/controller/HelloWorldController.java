package com.letshadow.back.controller;

import com.letshadow.back.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class HelloWorldController {

    @GetMapping
    public String hello(){
        return "hello";
    }

    @GetMapping(value = "/exception")
    public String helloException(){
        throw new RuntimeException("Hello RuntimeException");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(){
        return new ResponseEntity<>(ErrorResponse.of(500,"알 수 없는 서버 오류가 발생하였습니다"),HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
