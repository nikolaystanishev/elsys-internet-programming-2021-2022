package com.example.quizgame2.run;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HttpRun {
    @Autowired
    private Questions questions;


    @GetMapping("/question")
    public Question getRandomQuestion() {
//        return json of question and its id
        return questions.getRandomQuestion();
    }

    @PostMapping("/question/{id}")
    public ResponseEntity<?> getRandomAnswer(@RequestBody String answer, @PathVariable String id) {
        Question question = questions.getQuestionById(id);
        if (question == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else if (question.validateAnswer(answer)) {
            return new ResponseEntity<>("Correct", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Wrong", HttpStatus.OK);
        }
    }
}
