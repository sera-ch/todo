package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.TaskCategory;
import lombok.Getter;

@Getter
public class TaskCreateRequest {

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "category", required = true)
    private TaskCategory category;
}
