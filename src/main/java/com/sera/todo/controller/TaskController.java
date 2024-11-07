package com.sera.todo.controller;

import com.sera.todo.common.annotation.AdminPermission;
import com.sera.todo.controller.dto.request.*;
import com.sera.todo.controller.dto.response.TaskResponse;
import com.sera.todo.controller.dto.response.TaskUpdateStatusResponse;
import com.sera.todo.controller.dto.response.UserResponse;
import com.sera.todo.domain.entity.User;
import com.sera.todo.service.ChecklistService;
import com.sera.todo.service.TaskService;
import com.sera.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
@Validated
public class TaskController {

    private final TaskService taskService;
    private final ChecklistService checklistService;

    @PutMapping("/{id}/update-status")
    public ResponseEntity<TaskUpdateStatusResponse> updateTaskStatus(@AdminPermission @RequestHeader("token") final String token,
                                                                     @PathVariable("id") final Long taskId,
                                                                     @RequestBody final TaskUpdateStatusRequest request) {
        return ResponseEntity.ok(new TaskUpdateStatusResponse(this.checklistService.updateStatus(taskId, request.getCompleted())));
    }

    @PostMapping("/create")
    public ResponseEntity<TaskResponse> create(@AdminPermission @RequestHeader("token") final String token,
                                               @RequestBody final TaskCreateRequest request) {
        return ResponseEntity.ok(new TaskResponse(this.taskService.create(request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@AdminPermission @RequestHeader("token") final String token,
                                          @PathVariable(value = "id") final Long taskId) {
        return ResponseEntity.ok(this.taskService.delete(taskId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> update(@AdminPermission @RequestHeader("token") final String token,
                                               @PathVariable(value = "id") final Long taskId,
                                               @RequestBody final TaskUpdateRequest request) {
        return ResponseEntity.ok(new TaskResponse(this.taskService.update(request)));
    }
}
