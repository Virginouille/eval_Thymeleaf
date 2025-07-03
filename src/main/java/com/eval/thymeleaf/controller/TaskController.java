package com.eval.thymeleaf.controller;

import com.eval.thymeleaf.model.Project;
import com.eval.thymeleaf.model.Task;
import com.eval.thymeleaf.model.TaskStatus;
import com.eval.thymeleaf.model.User;
import com.eval.thymeleaf.service.ProjectService;
import com.eval.thymeleaf.service.TaskService;
import com.eval.thymeleaf.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;
    private final ProjectService projectService;

    @GetMapping
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "tasks";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("projects", projectService.getAllProjects());
        return "task-create";
    }

    @PostMapping("/create")
    public String createTask(@ModelAttribute Task task,
                             @RequestParam Long projectId,
                             @RequestParam Long assigneeId) {


        User assignee = userService.getUserById(assigneeId);
        Project project = projectService.getProjectById(projectId);

        if (assignee == null || project == null) {

            return "redirect:/tasks/create?error";
        }

        task.setStatus(TaskStatus.TODO);
        task.setAssignee(assignee);


        Task newTask = taskService.addTask(task.getTitle(), task.getStatus(), assignee);


        project.getTasks().add(newTask);

        return "redirect:/tasks";
    }


    @GetMapping("/{id}")
    public String showTaskDetails(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        if (task == null) {
            return "redirect:/tasks";
        }
        model.addAttribute("task", task);
        return "task-detail";
    }
}
