package org.elsys.ip.quizgame.rest;

import org.elsys.ip.quizgame.model.Question;
import org.elsys.ip.quizgame.model.QuestionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("question")
public class QuestionController {
    private final QuestionRepository repository;

    public QuestionController(QuestionRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public Question getQuestion(String questionId) {
        Optional<Question> question = repository.findById(UUID.fromString(questionId));
        if (question.isEmpty()) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        return question.get();
    }
}
