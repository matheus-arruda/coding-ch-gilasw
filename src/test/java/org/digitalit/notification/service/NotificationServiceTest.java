package org.digitalit.notification.service;

import static org.mockito.Mockito.*;

import java.util.*;
import org.digitalit.notification.dto.NotificationRequestDTO;
import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.digitalit.notification.repository.UserRepository;
import org.digitalit.notification.strategy.NotificationStrategy;
import org.digitalit.notification.strategy.NotificationStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class NotificationServiceTest {

  @Mock private UserRepository userRepository;

  @Mock private LogService logService;

  @Mock private NotificationStrategyFactory strategyFactory;

  @Mock private NotificationStrategy mockStrategy;

  @InjectMocks private NotificationService notificationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void sendNotification_ShouldCallStrategyAndLog_WhenCategoryIsSubscribed() {
    // Given
    CategoryEntity techCategory = new CategoryEntity();
    techCategory.setCode("SPORTS");
    techCategory.setName("Sports");

    UserEntity user =
        UserEntity.builder()
            .id(1L)
            .name("Matheus")
            .email("matheus@example.com")
            .channels(Set.of(NotificationChannel.EMAIL))
            .subscribedCategories(Set.of(techCategory))
            .build();

    when(userRepository.findAll()).thenReturn(List.of(user));
    when(strategyFactory.getStrategy(NotificationChannel.EMAIL)).thenReturn(mockStrategy);

    NotificationRequestDTO request = new NotificationRequestDTO();
    request.setMessage("Test Message");
    request.setCategory("SPORTS");

    // When
    notificationService.sendNotification(request);

    // Then
    verify(mockStrategy).send(user, "Test Message", "SPORTS");
    verify(logService).saveLog(user, NotificationChannel.EMAIL, "Test Message", "SPORTS");
  }

  @Test
  void sendNotification_ShouldNotSend_WhenCategoryIsNotSubscribed() {
    // Given
    CategoryEntity otherCategory = new CategoryEntity();
    otherCategory.setCode("SPORTS");
    otherCategory.setName("Sports");

    UserEntity user =
        UserEntity.builder()
            .id(1L)
            .name("Luana")
            .email("luana@example.com")
            .channels(Set.of(NotificationChannel.EMAIL))
            .subscribedCategories(Set.of(otherCategory)) // Não TECH
            .build();

    when(userRepository.findAll()).thenReturn(List.of(user));

    NotificationRequestDTO request = new NotificationRequestDTO();
    request.setCategory("TECH");
    request.setMessage("Msg");

    // When
    notificationService.sendNotification(request);

    // Then
    verify(strategyFactory, never()).getStrategy(any());
    verify(logService, never()).saveLog(any(), any(), any(), any());
  }
}
