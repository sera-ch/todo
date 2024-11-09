package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ChecklistCategory;
import lombok.Getter;

import java.util.List;

@Getter
public class ChecklistUpdateRequest {
    @JsonProperty(value = "tasks", required = true)
    private List<TaskUpdateStatusRequest> tasks;
}
