package com.cristian.taskmanager.repository;

import com.cristian.taskmanager.model.Task;
import com.cristian.taskmanager.model.TaskPriority;
import com.cristian.taskmanager.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository //NO OLVIDAR LAS ANOTACIONES
public interface TaskRepository extends JpaRepository<Task,Long> {
//LA INTERFAZ HEREDA DE JPAREPOSITORY <clase, tipo id>
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);

}
