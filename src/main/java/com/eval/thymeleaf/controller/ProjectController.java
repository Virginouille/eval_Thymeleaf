package com.eval.thymeleaf.controller;

import com.eval.thymeleaf.model.Project;
import com.eval.thymeleaf.model.Task;
import com.eval.thymeleaf.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {

    // Iinstanciation d'utilisateurs
    private final List<User> users = List.of(
            new User(1L, "alice"),
            new User(2L, "bob")
    );

    // Instanciation de tâches
    private final List<Task> tasks1 = List.of(
            new Task(1L, "Tâche 1", "TODO", users.get(0)),
            new Task(2L, "Tâche 2", "IN_PROGRESS", users.get(1))
    );

    private final List<Task> tasks2 = List.of(
            new Task(3L, "Tâche 3", "DONE", users.get(0))
    );

    // Instanciation des projets en mémoire
    private final List<Project> projects = new ArrayList<>(List.of(
            new Project(1L, "Projet Alpha", users.get(0), new ArrayList<>(tasks1)),
            new Project(2L, "Projet Beta", users.get(1), new ArrayList<>(tasks2))
    ));

    @GetMapping("/projects")
    public String projects(Model model) {
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/projects/{id}")
    public String project(@PathVariable Long id, Model model) {

        Project projectFound = null;
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                projectFound = project;
                break;
            }
        }

        if (projectFound == null) {
            return "redirect:/projects";
        }

        model.addAttribute("project", projectFound);
        return "project";
    }
}
