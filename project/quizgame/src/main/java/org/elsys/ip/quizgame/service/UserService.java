package org.elsys.ip.quizgame.service;

import org.elsys.ip.quizgame.error.UserAlreadyExistsException;
import org.elsys.ip.quizgame.model.User;
import org.elsys.ip.quizgame.model.UserRepository;
import org.elsys.ip.quizgame.web.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void registerNewUserAccount(UserDto userDto) throws UserAlreadyExistsException {
        if (isEmailAlreadyRegistered(userDto.getEmail())) {
            throw new UserAlreadyExistsException("User with that email (" + userDto.getEmail() + ") is already registered.");
        }

        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRoles(List.of("ROLE_USER"));

        userRepository.save(user);
    }

    private boolean isEmailAlreadyRegistered(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
