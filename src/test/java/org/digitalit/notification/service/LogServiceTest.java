package org.digitalit.notification.service;

import org.digitalit.notification.entity.NotificationLogEntity;
import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.digitalit.notification.repository.NotificationLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

class LogServiceTest {

    @Mock
    private NotificationLogRepository logRepository;

    @InjectMocks
    private LogService logService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveLog_ShouldSaveLogWithCorrectData() {
        // Given
        UserEntity user = UserEntity.builder()
                .id(1L)
                .name("Matheus")
                .email("matheus@example.com")
                .build();

        String message = "Test Message";
        String category = "TECH";
        NotificationChannel channel = NotificationChannel.EMAIL;

        // When
        logService.saveLog(user, channel, message, category);

        // Then
        ArgumentCaptor<NotificationLogEntity> logCaptor = ArgumentCaptor.forClass(NotificationLogEntity.class);
        verify(logRepository).save(logCaptor.capture());

        NotificationLogEntity captured = logCaptor.getValue();
        assertThat(captured.getUser()).isEqualTo(user);
        assertThat(captured.getUserName()).isEqualTo("Matheus");
        assertThat(captured.getUserEmail()).isEqualTo("matheus@example.com");
        assertThat(captured.getChannel()).isEqualTo("EMAIL");
        assertThat(captured.getMessage()).isEqualTo("Test Message");
        assertThat(captured.getCategory()).isEqualTo("TECH");
        assertThat(captured.getTimestamp()).isNotNull();
    }

    @Test
    void getAllLogs_ShouldReturnListOfLogs() {
        // Given
        List<NotificationLogEntity> mockLogs = List.of(new NotificationLogEntity());
        when(logRepository.findAll()).thenReturn(mockLogs);

        // When
        List<NotificationLogEntity> result = logService.getAllLogs();

        // Then
        assertThat(result).isEqualTo(mockLogs);
        verify(logRepository).findAll();
    }
}
