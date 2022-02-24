package org.elsys.ip.quizgame.rest;

import org.assertj.core.util.Lists;
import org.elsys.ip.quizgame.error.UserAlreadyExistsException;
import org.elsys.ip.quizgame.model.Answer;
import org.elsys.ip.quizgame.model.Question;
import org.elsys.ip.quizgame.model.QuestionRepository;
import org.elsys.ip.quizgame.service.UserService;
import org.elsys.ip.quizgame.web.model.UserDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private QuestionRepository repository;

    @Autowired
    private UserService userService;

    private String questionId;

    @BeforeAll
    public void setUp() throws UserAlreadyExistsException {
        UserDto user = new UserDto();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("email@email.com");
        user.setPassword("password");
        userService.registerNewUserAccount(user);

        Question question = new Question();
        question.setText("2 + 2 = ?");
        Answer answer1 = new Answer();
        answer1.setText("1");
        Answer answer2 = new Answer();
        answer2.setText("2");
        Answer answer3 = new Answer();
        answer3.setText("3");
        Answer answer4 = new Answer();
        answer4.setText("4");
        answer4.setCorrect(true);
        question.setAnswers(Lists.newArrayList(answer1, answer2, answer3, answer4));

        repository.save(question);

        questionId = question.getId().toString();
    }

//    @AfterAll
//    public void tearDown() {
//        repository.deleteAll();
//    }

    @Test
    public void getQuestionById() {
        Question question = restTemplate.withBasicAuth("email@email.com", "password").getForObject("http://localhost:" + port + "/question?questionId=" + questionId, Question.class);

        assertThat(question.getText()).isEqualTo("2 + 2 = ?");
    }

    @Test
    public void getMissingQuestionById() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("email@email.com", "password").getForEntity("http://localhost:" + port + "/question?questionId=" + UUID.randomUUID().toString(), String.class);

        assertThat(response.getStatusCode()).isEqualTo(NOT_FOUND);
    }
}
