package com.sera.todo.domain.repository;

import com.sera.todo.domain.entity.Checklist;
import com.sera.todo.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChecklistRepository extends JpaRepository<Checklist, Long> {
}
