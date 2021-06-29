package com.example.assignment.config;

import com.example.assignment.dto.ErrorDto;
import com.example.assignment.exception.MyNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

public class GlobalExceptionHandler {

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ErrorDto> generateException(MyNotFoundException re){
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus("404");
        dto.setErrorMessage(re.getMessage());

        return  new ResponseEntity<ErrorDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> generateException(RuntimeException re){
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus("500");
        dto.setErrorMessage(re.getMessage());

        return  new ResponseEntity<ErrorDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
