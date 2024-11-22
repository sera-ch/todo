package com.sera.todo.controller.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ChecklistFavoriteRequest {
    @JsonProperty(value = "is_favorite", required = true)
    boolean isFavorite;
}
