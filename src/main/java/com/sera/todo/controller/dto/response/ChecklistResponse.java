package com.sera.todo.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.entity.Checklist;
import com.sera.todo.domain.entity.Task;
import com.sera.todo.domain.enumeration.ChecklistCategory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChecklistResponse {
    private Long id;
    private String name;
    private ChecklistCategory category;

    @JsonProperty("completed_percent")
    private double completedPercent;

    private List<TaskResponse> tasks;

    public ChecklistResponse(final Checklist checklist) {
        this.id = checklist.getId();
        this.name = checklist.getName();
        this.category = checklist.getCategory();
        this.tasks = checklist.getTasks().stream().map(TaskResponse::new).toList();
        this.completedPercent = checklist.getCompletedPercent();
    }
}
