package com.mise.hotspot.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(originPatterns = "*", allowCredentials = "true")
@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello() {
        return "Hello, Hotel!";
    }
    
}
