package org.digitalit.notification.strategy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotificationStrategyFactoryTest {

  private NotificationStrategy emailStrategy;
  private NotificationStrategy smsStrategy;
  private NotificationStrategyFactory factory;

  @BeforeEach
  void setUp() {
    // Cria mocks das estratégias
    emailStrategy = mock(NotificationStrategy.class);
    when(emailStrategy.getChannel()).thenReturn(NotificationChannel.EMAIL);

    smsStrategy = mock(NotificationStrategy.class);
    when(smsStrategy.getChannel()).thenReturn(NotificationChannel.SMS);

    // Instancia a factory com os mocks
    factory = new NotificationStrategyFactory(List.of(emailStrategy, smsStrategy));
  }

  @Test
  void shouldReturnEmailStrategy() {
    NotificationStrategy result = factory.getStrategy(NotificationChannel.EMAIL);
    assertEquals(emailStrategy, result);
  }

  @Test
  void shouldReturnSmsStrategy() {
    NotificationStrategy result = factory.getStrategy(NotificationChannel.SMS);
    assertEquals(smsStrategy, result);
  }

  @Test
  void shouldReturnNullWhenChannelNotRegistered() {
    NotificationStrategy result =
        factory.getStrategy(NotificationChannel.PUSH); // não mockamos o PUSH
    assertNull(result);
  }
}
