package com.example.demo.DItest;

import com.example.demo.Tire.Tire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private final Tire tire;

    @Autowired
    public Car(Tire tire) {
        this.tire = tire;
    }

    public void printTireBrand() {
        System.out.println("장착된 타이어 브랜드 : " + tire.getTireBrand());
    }
}
