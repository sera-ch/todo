package com.sera.todo.domain.enumeration;

import lombok.Getter;

@Getter
public enum ChecklistCategory {

    TRIP("Du lịch"),
    HOSPITAL("Bệnh viện"),
    ;

    private String name;

    ChecklistCategory(final String name) {
        this.name = name;
    }
}
