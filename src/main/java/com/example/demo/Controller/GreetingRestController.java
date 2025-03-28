package com.example.demo.Controller;

import com.example.demo.Service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController {
    private final GreetingService greetingService;

    @Autowired
    public GreetingRestController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/api/hello")
    public String helloApi() {
        return greetingService.greet();
    }
}
