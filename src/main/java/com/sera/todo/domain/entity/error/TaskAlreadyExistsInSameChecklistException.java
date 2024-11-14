package com.sera.todo.domain.entity.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskAlreadyExistsInSameChecklistException extends RuntimeException {
    private Long checklistId;
    private String taskName;
}
