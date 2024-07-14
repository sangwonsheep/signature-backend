package com.example.todo_server.service;

import com.example.todo_server.model.Task;
import com.example.todo_server.persist.TaskRepository;
import com.example.todo_server.persist.entity.TaskEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDate;

import static com.example.todo_server.constants.TaskStatus.TO_DO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    // 실제 db와 연동하지 않는 방법
    // unit test를 하지 않으면 의존성에 문제인지, 비즈니스 로직의 문제인지 찾기 어려움.
    @Mock
    private TaskRepository taskRepository;

    // mock 객체를 포함해서 모든 의존성을 여기에 주입해줌.
    @InjectMocks
    private TaskService taskService;

    @Test
    @DisplayName("할 일 추가 기능 테스트")
    void add() {
        String title = "test";
        String description = "test description";
        LocalDate dueDate = LocalDate.now();

        // repository에 저장 1회 해야함.
        when(taskRepository.save(any(TaskEntity.class)))
                .thenAnswer(invocation -> {
                    TaskEntity e = (TaskEntity) invocation.getArgument(0);
                    e.setId(1L);
                    e.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                    e.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
                    return e;
                });

        Task actual = taskService.add(title, description, dueDate);

        // save가 1번 되었는지 검증
        verify(taskRepository, times(1)).save(any());

        assertEquals(1L, actual.getId());
        assertEquals(title, actual.getTitle());
        assertEquals(description, actual.getDescription());
        assertEquals(dueDate.toString(), actual.getDueDate());
        assertEquals(TO_DO, actual.getStatus());
        assertNotNull(actual.getCreatedAt());
        assertNotNull(actual.getUpdatedAt());
    }
}