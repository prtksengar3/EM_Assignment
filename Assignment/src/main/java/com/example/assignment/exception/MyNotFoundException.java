package com.example.assignment.exception;

public class MyNotFoundException extends RuntimeException{
    public MyNotFoundException(String str){
        super(str);
    }
}
