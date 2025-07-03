package com.eval.thymeleaf.controller;

import com.eval.thymeleaf.model.User;
import com.eval.thymeleaf.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", users);
        return "users";
    }

}
