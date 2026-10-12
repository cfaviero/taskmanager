package com.cristian.taskmanager.dto;

import com.cristian.taskmanager.model.TaskPriority;
import com.cristian.taskmanager.model.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

public record TaskResponseDTO (
    Long id,
    String title,
    String description,
    TaskStatus status,
    TaskPriority priority,
    LocalDateTime dueDate,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
){}