package com.fc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.fc.dao")
@SpringBootApplication
public class StudentScoreSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentScoreSystemApplication.class, args);
    }

}
