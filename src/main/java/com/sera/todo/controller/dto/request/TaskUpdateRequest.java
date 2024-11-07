package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.TaskCategory;
import lombok.Getter;

@Getter
public class TaskUpdateRequest {

    @JsonProperty(value = "task_id", required = true)
    private Long taskId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "category")
    private TaskCategory category;
}
