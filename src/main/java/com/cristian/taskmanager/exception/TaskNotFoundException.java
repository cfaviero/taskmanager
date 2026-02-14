package com.cristian.taskmanager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(Long id) {
        super("Tarea con id " + id + " no encontrada");
    }
}
