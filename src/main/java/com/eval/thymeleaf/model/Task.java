package com.eval.thymeleaf.model;

import lombok.Data;

@Data
public class Task {

    private Long id;
    private String title;
    private TaskStatus status;
    private User assignee;
}
