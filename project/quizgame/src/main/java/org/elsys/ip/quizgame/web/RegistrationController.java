package org.elsys.ip.quizgame.web;

import org.elsys.ip.quizgame.error.UserAlreadyExistsException;
import org.elsys.ip.quizgame.service.UserService;
import org.elsys.ip.quizgame.web.model.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping("/user/registration")
    public String showRegistrationForm(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        model.addAttribute("errors", new HashMap<String, String>());
        return "registration";
    }

    @PostMapping("/user/registration")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        try {
            userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistsException e) {
            bindingResult.rejectValue("email", "user", "An account for that username/email already exists.");
            return "registration";
        }

        return "successful-registration";
    }
}
