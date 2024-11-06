package com.sera.todo.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class GetAllChecklistsResponse {
    private List<ChecklistResponse> checklists;
}
