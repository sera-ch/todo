package com.sera.todo.service;

import com.sera.todo.common.Calculator;
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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ChecklistService {
    private final ChecklistRepository checklistRepository;
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    private static final Comparator<Checklist> FAVORITE_COMPARATOR = new Comparator<Checklist>() {
        @Override
        public int compare(Checklist c1, Checklist c2) {
            if (c1.isFavorite() && !c2.isFavorite()) {
                return -1;
            }
            if (!c1.isFavorite() && c2.isFavorite()) {
                return 1;
            }
            return 0;
        }
    };

    public List<Checklist> findAll() {
        List<Checklist> result = checklistRepository.findAll();
        result.sort(FAVORITE_COMPARATOR);
        return result;
    }

    public Checklist create(final ChecklistCreateRequest request) {
        Checklist newChecklist = this.checklistRepository.save(Checklist.builder()
                .name(request.getName())
                .category(request.getCategory())
                .completedPercent(0)
                .isFavorite(request.getIsFavorite() != null && request.getIsFavorite())
                .build());
        if (request.getTasks() != null || !request.getTasks().isEmpty()) {
            newChecklist.setTasks(this.taskService.create(request.getTasks(), newChecklist));
        }
        return newChecklist;
    }

    public Task updateStatus(final long taskId, final boolean completed) {
        final Task task = this.taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        if (task.isCompleted() == completed) {
            return task;
        }
        task.setCompleted(completed);
        final Checklist checklist = task.getChecklist();
        checklist.setCompletedPercent(Calculator.calculateCompletedPercent(checklist));
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
        if (request.getChecklistName() != null) {
            checklist.setName(request.getChecklistName());
        }
        if (request.getCategory() != null) {
            checklist.setCategory(request.getCategory());
        }
        if (request.getIsFavorite() != null) {
            checklist.setFavorite(request.getIsFavorite());
        }
        if (request.getTasks() != null && request.getTasks().size() > 0) {
            checklist.getTasks().forEach(task -> task.setCompleted(request.getTasks().stream().filter(t -> t.getTaskId() == task.getId()).findFirst().get().getCompleted()));
        }
        checklist.setCompletedPercent(Calculator.calculateCompletedPercent(checklist));
        return checklist;
    }

    public Checklist updateFavorite(final long checklistId, final boolean isFavorite) {
        final Checklist checklist = this.checklistRepository.findById(checklistId).orElseThrow(() -> new ChecklistNotFoundException(checklistId));
        checklist.setFavorite(isFavorite);
        return this.checklistRepository.save(checklist);
    }
}
