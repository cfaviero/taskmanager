package com.cristian.taskmanager.dto;

import com.cristian.taskmanager.model.TaskPriority;
import com.cristian.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record TaskUpdateDTO (
    @NotBlank
    @Size(max = 100, message = "El titulo no puede exceder 100 caracteres")
    String title,
    @Size(max = 500, message = "La descripcion no puede exceder 500 caracteres")
    String description,
    TaskStatus status,
    TaskPriority priority,
    LocalDateTime dueDate

){}
