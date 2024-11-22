package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ChecklistCategory;
import lombok.Getter;

import java.util.List;

@Getter
public class ChecklistUpdateRequest {

    @JsonProperty(value = "name")
    private String checklistName;

    @JsonProperty(value = "category")
    private ChecklistCategory category;

    @JsonProperty(value = "tasks")
    private List<TaskUpdateStatusRequest> tasks;

    @JsonProperty(value = "is_favorite")
    private Boolean isFavorite;
}
