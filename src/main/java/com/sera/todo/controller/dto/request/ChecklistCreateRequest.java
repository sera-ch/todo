package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ChecklistCategory;
import lombok.Getter;

import java.util.List;

@Getter
public class ChecklistCreateRequest {

    @JsonProperty(value = "name", required = true)
    private String name;

    @JsonProperty(value = "category", required = true)
    private ChecklistCategory category;

    @JsonProperty(value = "tasks", required = true)
    private List<TaskCreateRequest> tasks;
}
