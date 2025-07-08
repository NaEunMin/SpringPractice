package com.example.demo.Tire;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
@Primary
public class JapanTire implements Tire {

    @Override
    public String getTireBrand() {
        return "일본타이어";
    }
}
