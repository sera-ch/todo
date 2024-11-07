package com.sera.todo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/health")
@CrossOrigin
public class HealthCheckController {
    @GetMapping("/alive")
    public ResponseEntity healthCheck() {
        return ResponseEntity.ok().build();
    }
}
