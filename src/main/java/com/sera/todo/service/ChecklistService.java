package com.sera.todo.service;

import com.sera.todo.controller.dto.request.ChecklistCreateRequest;
import com.sera.todo.controller.dto.request.ChecklistUpdateRequest;
import com.sera.todo.controller.dto.request.TaskUpdateStatusRequest;
import com.sera.todo.domain.entity.Checklist;
import com.sera.todo.domain.entity.Task;
import com.sera.todo.domain.entity.error.ChecklistNotFoundException;
import com.sera.todo.domain.entity.error.TaskNotFoundException;
import com.sera.todo.domain.enumeration.ChecklistCategory;
import com.sera.todo.domain.repository.ChecklistRepository;
import com.sera.todo.domain.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    public List<Checklist> findAll() {
        return checklistRepository.findAll();
    }

    public Checklist create(final ChecklistCreateRequest request) {
        Checklist newChecklist = this.checklistRepository.save(Checklist.builder()
                .name(request.getName())
                .category(request.getCategory())
                .completedPercent(0)
                .build());
        newChecklist.setTasks(this.taskService.create(request.getTasks(), newChecklist));
        return newChecklist;
    }

    public Task updateStatus(final long taskId, final boolean completed) {
        final Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (task.isCompleted() == completed) {
            return task;
        }
        task.setCompleted(completed);
        final Checklist checklist = task.getChecklist();
        checklist.setCompletedPercent(this.calculateCompletedPercent(checklist));
        return task;
    }

    public Boolean delete(final long checklistId) {
        final Checklist checklist = this.checklistRepository.findById(checklistId).orElseThrow(() -> new ChecklistNotFoundException(checklistId));
        this.taskRepository.deleteAll(checklist.getTasks());
        this.checklistRepository.delete(checklist);
        return true;
    }

    public Checklist update(final long checklistId, final ChecklistUpdateRequest request) {
        final Checklist checklist = this.checklistRepository.findById(checklistId).orElseThrow(() -> new ChecklistNotFoundException(checklistId));
        checklist.getTasks().forEach(task -> task.setCompleted(request.getTasks().stream().filter(t -> t.getTaskId() == task.getId()).findFirst().get().getCompleted()));
        checklist.setCompletedPercent(this.calculateCompletedPercent(checklist));
        return checklist;
    }

    private double calculateCompletedPercent(final Checklist checklist) {
        final List<Task> tasks = checklist.getTasks();
        final long completed = tasks.stream().filter(Task::isCompleted).count();
        return Math.floorDiv(completed * 100, tasks.size());
    }
}
