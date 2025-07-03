package com.eval.thymeleaf.service;

import com.eval.thymeleaf.model.Project;
import com.eval.thymeleaf.model.Task;
import com.eval.thymeleaf.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private long currentProjectId = 1;
    private final List<Project> projects = new ArrayList<>();

    /**Méthode qui permet d'ajouter un projet*/
    public Project addProject(String name, User creator, List<Task> tasks) {
        Project project = new Project();
        project.setId(currentProjectId++);
        project.setName(name);
        project.setCreator(creator);
        project.setTasks(tasks);
        projects.add(project);
        return project;
    }

    /**Méthode qui retourne tous les projects*/
    public List<Project> getAllProjects() {
        return projects;
    }

    /**Méthode qui recupère un project via son id*/
    public Project getProjectById(long id) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        return null;
    }

    /**Méthode qui récupère un project via le nom du project*/
    public Project getProjectByProjectname(String name) {
        for (Project project : projects) {
            if (project.getName().equals(name)) {
                return project;
            }
        }
        return null;
    }

    /**Méthode qui permet de récupérer le nom du créateur du project*/
    public Project getProjectByCreatorName(String creatorName) {
        for (Project project : projects) {
            if (project.getCreator().getUsername().equals(creatorName)) {
                return project;
            }
        }
        return null;
    }

    /**Méthode qui récupère le nombre de tâches des project*/
    public int getNumberOfTasks() {
        int total = 0;
        for (Project project : projects) {
            total += project.getTasks().size();
        }
        return total;
    }

}
