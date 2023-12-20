package com.Ramaldanov.webcatalog.controller;

import com.Ramaldanov.webcatalog.entity.User;
import com.Ramaldanov.webcatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String showRegPage() {
        return "register";
    }

    @PostMapping
    public String registerUser(@RequestParam (defaultValue = "!@") String username,
                               @RequestParam (defaultValue = "!@") String password) {
        if(!(username.equals("!@") & password.equals("!@"))) {
            System.out.println(username + " " + password);
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            userService.saveUser(user);
        }
        return "redirect:/main";
    }

}
