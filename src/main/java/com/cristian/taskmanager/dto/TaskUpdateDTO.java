package com.cristian.taskmanager.dto;

import com.cristian.taskmanager.model.TaskPriority;
import com.cristian.taskmanager.model.TaskStatus;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskUpdateDTO {
    @Size(max = 100, message = "El titulo no puede exceder 100 caracteres")
    private String title;
    @Size(max = 500, message = "La descripcion no puede exceder 500 caracteres")
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDateTime dueDate;

}
