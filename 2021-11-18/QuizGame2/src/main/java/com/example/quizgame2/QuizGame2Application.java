package com.example.quizgame2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.quizgame2.run")
public class QuizGame2Application {

    public static void main(String[] args) {
        SpringApplication.run(QuizGame2Application.class, args);
    }

}
