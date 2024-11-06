package com.sera.todo.controller.dto.response;

import com.sera.todo.domain.entity.Task;
import com.sera.todo.domain.enumeration.TaskCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskResponse {
    private Long id;
    private String name;
    private TaskCategory category;
    private boolean checked;

    public TaskResponse(final Task task) {
        this.id = task.getId();
        this.name = task.getName();
        this.category = task.getCategory();
        this.checked = task.isCompleted();
    }
}
