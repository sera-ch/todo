package com.sera.todo.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskAlreadyExistsInSameChecklistExceptionResponse extends ErrorResponse {

    @JsonProperty("checklist_id")
    private Long checklistId;

    @JsonProperty("task_name")
    private String taskName;

    public TaskAlreadyExistsInSameChecklistExceptionResponse(Long checklistId, String taskName) {
        super(ErrorCode.TASK_ALREADY_EXISTS_IN_SAME_CHECKLIST.getErrorCode(), ErrorCode.TASK_ALREADY_EXISTS_IN_SAME_CHECKLIST.getErrorMessage());
        this.checklistId = checklistId;
        this.taskName = taskName;
    }
}
