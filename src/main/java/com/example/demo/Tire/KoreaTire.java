package com.example.demo.Tire;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class KoreaTire implements Tire {

    @Override
    public String getTireBrand() {
        return "한국타이어";
    }
}
