package com.example.FitTogether;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.example.FitTogether.dao")
public class FitTogetherApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitTogetherApplication.class, args);
	}

}
