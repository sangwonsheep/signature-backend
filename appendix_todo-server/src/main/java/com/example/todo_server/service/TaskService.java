package com.example.todo_server.service;

import com.example.todo_server.constants.TaskStatus;
import com.example.todo_server.model.Task;
import com.example.todo_server.persist.TaskRepository;
import com.example.todo_server.persist.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task add(String title, String description, LocalDate dueDate) {
        TaskEntity entity = TaskEntity.builder()
                .title(title)
                .description(description)
                .dueDate(Date.valueOf(dueDate))
                .status(TaskStatus.TO_DO)
                .build();

        TaskEntity savedEntity = taskRepository.save(entity);
        return entityToDto(savedEntity);
    }

    public List<Task> getAll() {
        return taskRepository.findAll().stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<Task> getByDueDate(String dueDate) {
        return taskRepository.findAllByDueDate(Date.valueOf(dueDate)).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public List<Task> getByStatus(TaskStatus status) {
        return taskRepository.findAllByStatus(status).stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    public Task getOne(Long id) {
        TaskEntity entity = getById(id);
        return entityToDto(entity);
    }

    public Task update(Long id, String title, String description, LocalDate dueDate) {
        TaskEntity exists = getById(id);

        exists.setTitle(Strings.isEmpty(title) ?
                exists.getTitle() : title);

        exists.setDescription(Strings.isEmpty(description) ?
                exists.getDescription() : description);

        exists.setDueDate(Objects.isNull(dueDate) ?
                exists.getDueDate() : Date.valueOf(dueDate));

        TaskEntity updated = taskRepository.save(exists);
        return entityToDto(updated);
    }

    public Task updateStatus(Long id, TaskStatus status) {
        TaskEntity entity = getById(id);
        entity.setStatus(status);
        TaskEntity saved = taskRepository.save(entity);
        return entityToDto(saved);
    }

    public boolean delete(Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (Exception e) {
            log.error("an error occurred while deleting {}", e.toString());
            return false;
        }
        return true;
    }

    private TaskEntity getById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(String.format("not exists task id [%d]", id)));
    }

    private Task entityToDto(TaskEntity entity) {
        return Task.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .dueDate(entity.getDueDate().toString())
                .createdAt(entity.getCreatedAt().toLocalDateTime())
                .updatedAt(entity.getUpdatedAt().toLocalDateTime())
                .build();
    }
}
