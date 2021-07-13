package com.example.assignment.config;

import com.example.assignment.dto.ErrorDto;
import com.example.assignment.exception.MyNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import java.util.Date;
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> generateException(ResponseStatusException re){
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus(String.valueOf(re.getStatus().value()));
        dto.setErrorMessage(re.getMessage());
        log.error("Exception occurred : ",re);
        return  new ResponseEntity<>(dto, re.getStatus());
    }

    @ExceptionHandler(MyNotFoundException.class)
    public ResponseEntity<ErrorDto> generateException(MyNotFoundException re){
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus("404");
        dto.setErrorMessage(re.getMessage());
        log.error("Exception occurred : ",re);

        return  new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDto> generateException(RuntimeException re){
        ErrorDto dto = new ErrorDto();
        dto.setTimeStamp(new Date().toString());
        dto.setStatus("500");
        dto.setErrorMessage(re.getMessage());
        log.error("Exception occurred : ",re);

        return  new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
