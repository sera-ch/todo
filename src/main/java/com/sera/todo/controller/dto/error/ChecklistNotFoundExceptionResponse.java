package com.sera.todo.controller.dto.error;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sera.todo.domain.enumeration.ErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChecklistNotFoundExceptionResponse extends ErrorResponse{

    @JsonProperty(value = "checklist_id")
    private Long checklistId;

    public ChecklistNotFoundExceptionResponse(Long checklistId) {
        super(ErrorCode.CHECKLIST_NOT_FOUND.getErrorCode(), ErrorCode.CHECKLIST_NOT_FOUND.getErrorMessage());
        this.checklistId = checklistId;
    }
}
