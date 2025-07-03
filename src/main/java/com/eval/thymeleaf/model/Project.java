package com.eval.thymeleaf.model;

import lombok.Data;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.List;

@Data
public class Project {

    private Long id;
    private String name;
    private User creator;
    private List<Task> tasks = new ArrayList<>();
}
