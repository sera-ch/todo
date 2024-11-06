package com.sera.todo.controller;

import com.sera.todo.common.annotation.AdminPermission;
import com.sera.todo.controller.dto.request.ChecklistCreateRequest;
import com.sera.todo.controller.dto.response.ChecklistResponse;
import com.sera.todo.controller.dto.response.GetAllChecklistsResponse;
import com.sera.todo.service.ChecklistService;
import com.sera.todo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/checklists")
@Validated
public class ChecklistController {

    private final ChecklistService checklistService;
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<GetAllChecklistsResponse> findAllChecklists() {
        return ResponseEntity.ok(GetAllChecklistsResponse.builder()
                .checklists(this.checklistService.findAll()
                        .stream().map(ChecklistResponse::new)
                .toList())
                .build());
    }

    @PostMapping(value = "/create")
    public ResponseEntity<ChecklistResponse> createChecklist(@AdminPermission @RequestHeader(value = "token") final String token, @RequestBody final ChecklistCreateRequest request) {
        return ResponseEntity.ok(new ChecklistResponse(this.checklistService.create(request)));
    }
}
