package com.eval.thymeleaf.controller;

import com.eval.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    //Instanciation de users en mémoire
    private final List<User> users = new ArrayList<>(
            List.of(
                    new User(1L, "alice"),
                    new User(2L, "bob"),
                    new User(3L, "charlie")
            )
    );

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", users);
        return "users";
    }

}
