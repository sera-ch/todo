package com.sera.todo.controller;

import com.sera.todo.common.annotation.AdminPermission;
import com.sera.todo.controller.dto.request.TaskUpdateStatusRequest;
import com.sera.todo.controller.dto.request.UserLoginRequest;
import com.sera.todo.controller.dto.request.UserRegisterRequest;
import com.sera.todo.controller.dto.response.TaskUpdateStatusResponse;
import com.sera.todo.controller.dto.response.UserResponse;
import com.sera.todo.domain.entity.User;
import com.sera.todo.service.ChecklistService;
import com.sera.todo.service.TaskService;
import com.sera.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final ChecklistService checklistService;

    @PutMapping("/{id}/update-status")
    public ResponseEntity<TaskUpdateStatusResponse> updateTaskStatus(@AdminPermission @RequestHeader("token") final String token,
                                                                     @PathVariable("id") final Long taskId,
                                                                     @RequestBody final TaskUpdateStatusRequest request) {
        return ResponseEntity.ok(new TaskUpdateStatusResponse(this.checklistService.updateStatus(taskId, request.getCompleted())));
    }
}
