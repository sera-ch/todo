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
public class TaskUpdateStatusResponse {
    private Long id;
    private boolean completed;

    public TaskUpdateStatusResponse(final Task task) {
        this.id = task.getId();
        this.completed = task.isCompleted();
    }
}
