package com.example.demo;

import com.example.demo.DItest.Car;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

//		SpringApplication.run(DemoApplication.class, args);
		ApplicationContext context = SpringApplication.run(DemoApplication.class, args);

		Car car = context.getBean(Car.class);
		car.printTireBrand();
	}

}
