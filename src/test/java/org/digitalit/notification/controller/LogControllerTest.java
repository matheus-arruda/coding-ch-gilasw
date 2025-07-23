package org.digitalit.notification.controller;

import org.digitalit.notification.entity.NotificationLogEntity;
import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.service.LogService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class LogControllerTest {

    @Test
    void shouldReturnAllLogs() throws Exception {
        // Arrange
        LogService mockService = Mockito.mock(LogService.class);
        LogController controller = new LogController(mockService);

        UserEntity user = UserEntity.builder()
                .id(1L)
                .name("Matheus")
                .email("matheush13@gmail.com")
                .build();

        List<NotificationLogEntity> logs = List.of(
                NotificationLogEntity.builder()
                        .id(1L)
                        .user(user)
                        .userName("Matheus")
                        .userEmail("matheush13@gmail.com")
                        .channel("EMAIL")
                        .message("Hello World")
                        .category("SPORTS")
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        when(mockService.getAllLogs()).thenReturn(logs);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        // Act & Assert
        mockMvc.perform(get("/logs"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].userName").value("Matheus"))
                .andExpect(jsonPath("$[0].channel").value("EMAIL"));
    }
}
