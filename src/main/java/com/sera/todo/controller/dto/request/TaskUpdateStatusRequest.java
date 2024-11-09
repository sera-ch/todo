package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class TaskUpdateStatusRequest {

    @JsonProperty("task_id")
    private Long taskId;

    @JsonProperty(value = "completed", required = true)
    private Boolean completed;
}
