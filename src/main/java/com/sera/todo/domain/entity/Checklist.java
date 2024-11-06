package com.sera.todo.domain.entity;

import com.sera.todo.domain.enumeration.ChecklistCategory;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "checklist")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "completed_percent")
    private double completedPercent;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private ChecklistCategory category;

    @OneToMany(mappedBy = "checklist",cascade = CascadeType.ALL,orphanRemoval = true)
    @OrderBy("id ASC")
    private List<Task> tasks;
}
