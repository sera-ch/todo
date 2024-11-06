package com.sera.todo.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskNotFoundExceptionResponse extends ErrorResponse{

    @JsonProperty(value = "task_id")
    private Long taskId;

    public TaskNotFoundExceptionResponse(Long taskId) {
        super(ErrorCode.TASK_NOT_FOUND.getErrorCode(), ErrorCode.TASK_NOT_FOUND.getErrorMessage());
        this.taskId = taskId;
    }
}
