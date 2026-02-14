package com.cristian.taskmanager.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity //Entidad
@Table(name = "tasks") //Crea tabla y le pone nombre
@Data //Lombok getters, setters, etc.
@NoArgsConstructor //Lombok Constructor vacio
@AllArgsConstructor //Lombok Constructor todos
public class Task {

    @Id //Identificador
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera automaticamente
    private Long id;
    @Column(nullable = false, length = 100) //No puede ser nulo ni mayor a 100
    private String title;
    @Column(length = 500) //No puede ser mayor a 500 y puede ser nulo
    private String description;
    @Enumerated(EnumType.STRING) //Como guardar el enum en BD (Ordinal y String)
    @Column(nullable = false)
    private TaskStatus status = TaskStatus.PENDING;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority = TaskPriority.MEDIUM;
    @Column(name = "due_date")
    private LocalDateTime dueDate;
    //Updatable evita que hibernate lo modifique en updates, solo se establece una vez
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
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
