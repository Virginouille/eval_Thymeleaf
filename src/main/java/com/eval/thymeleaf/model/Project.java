package com.eval.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    private Long id;
    private String name;
    private User creator;
    private List<Task> tasks = new ArrayList<>();
}
