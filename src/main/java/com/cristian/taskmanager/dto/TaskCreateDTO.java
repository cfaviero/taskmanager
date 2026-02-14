package com.cristian.taskmanager.dto;

import com.cristian.taskmanager.model.TaskPriority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateDTO {
    @NotBlank(message = "El titulo es obligatorio")
    @Size(max = 100, message = "El titulo no puede exceder 100 caracteres")
    private String title;
    @Size(max = 500, message = "La descripcion no puede exceder 500 caracteres")
    private String description;
    private TaskPriority priority = TaskPriority.MEDIUM;
    private LocalDateTime dueDate;

}
