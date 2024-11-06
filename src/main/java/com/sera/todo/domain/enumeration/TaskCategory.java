package com.sera.todo.domain.enumeration;

import lombok.Getter;

@Getter
public enum TaskCategory {

    WORK("Làm việc"),
    ENTERTAINMENT("Chơi"),
    MEDICINE("Thuốc"),
    ;


    private String name;

    TaskCategory(final String name) {
        this.name = name;
    }
}
