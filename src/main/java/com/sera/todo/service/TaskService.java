package com.sera.todo.service;

import com.sera.todo.controller.dto.request.TaskCreateRequest;
import com.sera.todo.controller.dto.request.TaskUpdateStatusRequest;
import com.sera.todo.domain.entity.Checklist;
import com.sera.todo.domain.entity.Task;
import com.sera.todo.domain.enumeration.TaskCategory;
import com.sera.todo.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> create(List<TaskCreateRequest> requests, Checklist checklist) {
        final List<Task> tasks = new ArrayList<>();
        for(TaskCreateRequest request : requests) {
            final Task newTask = Task.builder()
                    .name(request.getName())
                    .category(request.getCategory())
                    .checklist(checklist)
                    .completed(false)
                    .build();
            tasks.add(this.taskRepository.save(newTask));
        }
        return tasks;
    }
}
