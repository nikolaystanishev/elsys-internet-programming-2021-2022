package com.example.quizgame2.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;

@Service
public class RunApp implements CommandLineRunner {
    @Autowired
    private Questions questions;

    @Override
    public void run(String... args) throws IOException {
        System.out.println("Starting the quiz!");

        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        while (true) {
            Question question = questions.getRandomQuestion();
            System.out.println(question);
            System.out.println("Enter your answer: ");
            String answer = reader.readLine();
            answerValidation(question, answer);
        }
    }

    private void answerValidation(Question question, String answer) {
        if (answer.equalsIgnoreCase("exit")) {
            System.exit(0);
        } else if (question.validateAnswer(answer)) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong!");
        }
    }
}
