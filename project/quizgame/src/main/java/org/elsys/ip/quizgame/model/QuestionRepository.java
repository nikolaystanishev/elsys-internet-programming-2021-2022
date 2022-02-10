package org.elsys.ip.quizgame.model;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface QuestionRepository extends CrudRepository<Question, UUID> {
}
