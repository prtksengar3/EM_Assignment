package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api1/v1/")
public class home {

    @RequestMapping("/welcome")
    public  String welcome(){
        String text ="this is welcome page";
        return text;
    }
}
