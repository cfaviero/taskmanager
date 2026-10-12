package com.cristian.taskmanager.service;

import com.cristian.taskmanager.dto.TaskCreateDTO;
import com.cristian.taskmanager.dto.TaskResponseDTO;
import com.cristian.taskmanager.dto.TaskUpdateDTO;
import com.cristian.taskmanager.exception.TaskNotFoundException;
import com.cristian.taskmanager.model.Task;
import com.cristian.taskmanager.model.TaskPriority;
import com.cristian.taskmanager.model.TaskStatus;
import com.cristian.taskmanager.repository.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService (TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskResponseDTO createTask(TaskCreateDTO dto) {
        log.info("Creando nueva tarea: {}", dto.title());

        Task task = new Task();
        task.setTitle(dto.title());
        task.setDescription(dto.description());
        task.setPriority(dto.priority());
        task.setDueDate(dto.dueDate());
        task.setStatus(TaskStatus.PENDING);

        Task savedTask = taskRepository.save(task);
        log.info("Tarea creada con id: {}", savedTask.getId());

        return convertToDTO(savedTask);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getAllTasks() {
        log.info("Obteniendo todas las tareas");
        return taskRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TaskResponseDTO getTaskById(Long id) {
        log.info("Buscando tarea con id: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
        return convertToDTO(task);
    }

    public TaskResponseDTO updateTask(Long id, TaskUpdateDTO dto) {
        log.info("Actualizando tarea con id: {}", id);

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));

        if (dto.title() != null) {
            task.setTitle(dto.title());
        }
        if (dto.description() != null) {
            task.setDescription(dto.description());
        }
        if (dto.status() != null) {
            task.setStatus(dto.status());
        }
        if (dto.priority() != null) {
            task.setPriority(dto.priority());
        }
        if (dto.dueDate() != null) {
            task.setDueDate(dto.dueDate());
        }

        Task updatedTask = taskRepository.save(task);
        log.info("Tarea actualizada: {}", updatedTask.getId());

        return convertToDTO(updatedTask);
    }

    public void deleteTask(Long id) {
        log.info("Eliminando tarea con id: {}", id);

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }

        taskRepository.deleteById(id);
        log.info("Tarea eliminada: {}", id);
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksByStatus(TaskStatus status) {
        log.info("Buscando tareas con estado: {}", status);
        return taskRepository.findByStatus(status)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponseDTO> getTasksByPriority(TaskPriority priority) {
        log.info("Buscando tareas con prioridad: {}", priority);
        return taskRepository.findByPriority(priority)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private TaskResponseDTO convertToDTO(Task task) {
        return new TaskResponseDTO(
        task.getId(),
        task.getTitle(),
        task.getDescription(),
        task.getStatus(),
        task.getPriority(),
        task.getDueDate(),
        task.getCreatedAt(),
        task.getUpdatedAt()
        );
    }
}