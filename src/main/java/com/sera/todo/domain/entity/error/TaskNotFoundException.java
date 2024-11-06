package com.sera.todo.domain.entity.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TaskNotFoundException extends RuntimeException {
    private long taskId;
}
