package com.sera.todo.controller;

import com.sera.todo.common.annotation.AdminPermission;
import com.sera.todo.controller.dto.request.ChecklistCreateRequest;
import com.sera.todo.controller.dto.request.ChecklistUpdateRequest;
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
@CrossOrigin
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

    @PutMapping(value="/{id}/tasks")
    public ResponseEntity<ChecklistResponse> updateChecklist(@AdminPermission @RequestHeader(value = "token") final String token, @PathVariable(value = "id") final Long checklistId, @RequestBody final ChecklistUpdateRequest request) {
        return ResponseEntity.ok(new ChecklistResponse(this.checklistService.update(checklistId, request)));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteChecklist(@AdminPermission @RequestHeader(value = "token") final String token, @PathVariable(value = "id") final Long checklistId) {
        return ResponseEntity.ok(this.checklistService.delete(checklistId));
    }
}
