package com.eval.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private Long id;
    private String title;
    private TaskStatus status;
    private User assignee;
}
