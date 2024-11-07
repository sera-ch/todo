package com.sera.todo.service;

import com.sera.todo.controller.dto.request.TaskCreateRequest;
import com.sera.todo.controller.dto.request.TaskUpdateRequest;
import com.sera.todo.controller.dto.request.TaskUpdateStatusRequest;
import com.sera.todo.domain.entity.Checklist;
import com.sera.todo.domain.entity.Task;
import com.sera.todo.domain.entity.error.ChecklistNotFoundException;
import com.sera.todo.domain.entity.error.TaskNotFoundException;
import com.sera.todo.domain.repository.ChecklistRepository;
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
    private final ChecklistRepository checklistRepository;

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

    public Task create(TaskCreateRequest request) {
        final Checklist checklist = this.checklistRepository.findById(request.getChecklistId())
                .orElseThrow(() -> new ChecklistNotFoundException(request.getChecklistId()));
        return this.taskRepository.save(Task.builder()
                .name(request.getName())
                .category(request.getCategory())
                .checklist(checklist)
                .completed(false)
                .build());
    }

    public boolean delete(Long taskId) {
        final Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        this.taskRepository.delete(task);
        return true;
    }

    public Task update(TaskUpdateRequest request) {
        final Task task = this.taskRepository.findById(request.getTaskId()).orElseThrow(() -> new TaskNotFoundException(request.getTaskId()));
        if (task.getCategory() != request.getCategory()) {
            task.setCategory(request.getCategory());
        }
        if (task.getName() != request.getName()) {
            task.setName(request.getName());
        }
        return task;
    }
}
