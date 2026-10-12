package com.cristian.taskmanager.dto;

import com.cristian.taskmanager.model.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record TaskCreateDTO(
    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El titulo no debe superar los 100 caracteres.")
    String title,

    @Size(max = 500, message = "La descripcion no debe ser mas de 500 caracteres.")
    String description,

    TaskPriority priority,
    LocalDateTime dueDate
) {
    public TaskCreateDTO{
        if (priority == null) {
            priority = TaskPriority.MEDIUM;
        }
    }
}
