package com.sera.todo.common;

import com.sera.todo.domain.entity.Checklist;
import com.sera.todo.domain.entity.Task;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Calculator {

    public static double calculateCompletedPercent(final Checklist checklist) {
        final List<Task> tasks = checklist.getTasks();
        final long completed = tasks.stream().filter(Task::isCompleted).count();
        return Math.floorDiv(completed * 100, tasks.size());
    }

}
