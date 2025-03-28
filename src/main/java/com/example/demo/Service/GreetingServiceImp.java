package com.example.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class GreetingServiceImp implements GreetingService {
    @Override
    public String greet() {
        return "Hello World";
    }
}
