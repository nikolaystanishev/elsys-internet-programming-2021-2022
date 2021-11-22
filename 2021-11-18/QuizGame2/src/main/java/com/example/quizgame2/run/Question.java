package com.example.quizgame2.run;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Question {
    @JsonProperty
    private final UUID id = UUID.randomUUID();
    @JsonProperty
    private final String question;
    private final String answer;

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public boolean validateAnswer(String answer) {
        return this.answer.equalsIgnoreCase(answer);
    }

    @Override
    public String toString() {
        return question;
    }

    public UUID getId() {
        return id;
    }
}
