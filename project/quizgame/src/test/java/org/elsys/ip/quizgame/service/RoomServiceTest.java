package org.elsys.ip.quizgame.service;

import org.elsys.ip.quizgame.error.UserAlreadyExistsException;
import org.elsys.ip.quizgame.web.model.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.annotation.DirtiesContext;

import static org.springframework.test.annotation.DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD;

@SpringBootTest
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
public class RoomServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private RoomService roomService;

    @Test
    public void createAndRead() {
        // TODO: implement me as class work
    }

    @Test
    public void createMultipleAndRead() {
        // TODO: implement me as class work
    }

    @Test
    public void alreadyExist() {
        // TODO: implement me as class work
    }

    @BeforeEach
    public void setUp() throws UserAlreadyExistsException {
        UserDto user = new UserDto();
        user.setFirstName("Test");
        user.setLastName("User");
        user.setEmail("email@email.com");
        user.setPassword("password");
        userService.registerNewUserAccount(user);

        UserDetails userDetails = Mockito.mock(UserDetails.class);
        Mockito.when(userDetails.getUsername()).thenReturn("email@email.com");

        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);
    }

}
