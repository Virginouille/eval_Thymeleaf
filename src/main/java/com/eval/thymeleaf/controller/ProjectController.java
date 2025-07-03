package com.eval.thymeleaf.controller;

import com.eval.thymeleaf.model.Project;
import com.eval.thymeleaf.model.Task;
import com.eval.thymeleaf.model.TaskStatus;
import com.eval.thymeleaf.model.User;
import com.eval.thymeleaf.service.ProjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final List<User> users = List.of(
            new User(1L, "alice"),
            new User(2L, "bob")
    );

    private final List<Task> tasks1 = List.of(
            new Task(1L, "Tâche 1", TaskStatus.TODO, users.get(0)),
            new Task(2L, "Tâche 2", TaskStatus.IN_PROGRESS, users.get(1))
    );

    private final List<Task> tasks2 = List.of(
            new Task(3L, "Tâche 3", TaskStatus.DONE, users.get(0))
    );

    private final List<Project> projects = new ArrayList<>(List.of(
            new Project(1L, "Projet Alpha", users.get(0), new ArrayList<>(tasks1)),
            new Project(2L, "Projet Beta", users.get(1), new ArrayList<>(tasks2))
    ));

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public String projects(Model model) {
        model.addAttribute("projects", projects);
        return "projects";
    }

    @GetMapping("/{id}")
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

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("project", new Project());
        model.addAttribute("users", users);
        return "create-project";
    }

    @PostMapping("/create")
    public String addProject(@ModelAttribute("project") Project project) {
        User creator = users.stream()
                .filter(u -> u.getId().equals(project.getCreator().getId()))
                .findFirst()
                .orElse(null);
        if (creator == null) {
            return "redirect:/projects/create?error=creatorNotFound";
        }
        if (projectService != null) {
            projectService.addProject(project.getName(), creator, project.getTasks());
        } else {
            projects.add(new Project(
                    (long) (projects.size() + 1),
                    project.getName(),
                    creator,
                    new ArrayList<>()
            ));
        }
        return "redirect:/projects";
    }
}