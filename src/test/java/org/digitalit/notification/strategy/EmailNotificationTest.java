package org.digitalit.notification.strategy;

import static org.junit.jupiter.api.Assertions.*;

import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailNotificationTest {

  private EmailNotification strategy;

  @BeforeEach
  void setUp() {
    strategy = new EmailNotification();
  }

  @Test
  void shouldReturnEmailChannel() {
    assertEquals(NotificationChannel.EMAIL, strategy.getChannel());
  }

  @Test
  void shouldSendEmailWithoutErrors() {
    // Arrange
    UserEntity user = UserEntity.builder().email("test@example.com").build();

    String message = "Test message";
    String category = "SPORTS";

    // Act + Assert (just checking no exception is thrown)
    assertDoesNotThrow(() -> strategy.send(user, message, category));
  }
}
