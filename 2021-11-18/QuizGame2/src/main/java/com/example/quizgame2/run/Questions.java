package com.example.quizgame2.run;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class Questions {
    private final List<Question> questions = List.of(
            new Question("What is the capital of India?", "Delphi"),
            new Question("What is the capital of Germany?", "Berlin"),
            new Question("What is the capital of France?", "Paris"),
            new Question("What is the capital of Italy?", "Rome"),
            new Question("What is the capital of Spain?", "Madrid"),
            new Question("What is the capital of the United States?", "Washington D.C."),
            new Question("What is the capital of Madagascar?", "Antananarivo"),
            new Question("What is the capital of Bulgaria?", "Sofia"),
            new Question("What is the capital of Sofia?", "Gorna Banq"),
            new Question("Where is Bozhkov?", "In Bulgaria!"),
            new Question("Where is Volen?", "In Arabic!"),
            new Question("Where is Luna?", "In our hearts!"));

    public Question getRandomQuestion() {
        return questions.get((int) (Math.random() * questions.size()));
    }

    public Question getQuestionById(String id) {
        for (Question question : questions) {
            if (question.getId().toString().equals(id)) {
                return question;
            }
        }
        return null;
    }
}
