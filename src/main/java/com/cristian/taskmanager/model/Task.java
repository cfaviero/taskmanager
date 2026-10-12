package com.cristian.taskmanager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "El titulo es obligatorio.")
    @Size(max = 100, message = "El titulo no debe superar los 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String title;
    @Size(max = 500, message = "La descripcion no debe ser mas de 500 caracteres.")
    @Column(length = 500)
    private String description;
    @NotNull(message = "El estado es obligatorio.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;
    @NotNull(message = "La prioridad se debe especificar.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;
    @FutureOrPresent(message = "La fecha limite debe ser hoy o en el futuro.")
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    @NotNull
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @NotNull @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
