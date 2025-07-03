package com.eval.thymeleaf.service;

import com.eval.thymeleaf.model.Project;
import com.eval.thymeleaf.model.Task;
import com.eval.thymeleaf.model.TaskStatus;
import com.eval.thymeleaf.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

        private long currentTaskId = 1;
        private final List<Task> tasks = new ArrayList<>();

        /**Méthode qui permet d'ajouter une task*/
        public Task addTask(String title, TaskStatus status, User assignee) {
            Task task = new Task();
            task.setId(currentTaskId++);
            task.setTitle(title);
            task.setStatus(status);
            task.setAssignee(assignee);
            tasks.add(task);
            return task;
        }

        /**Méthode qui retourne tous les projects*/
        public List<Task> getAllTasks() {
            return tasks;
        }

        /**Méthode qui recupère un project via son id*/
        public Task getTaskById(long id) {
            for (Task task : tasks) {
                if (task.getId() == id) {
                    return task;
                }
            }
            return null;
        }

    }
