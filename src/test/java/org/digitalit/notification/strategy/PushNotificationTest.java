package org.digitalit.notification.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PushNotificationTest {

    private PushNotification pushNotification;

    @BeforeEach
    void setUp() {
        pushNotification = new PushNotification();
    }

    @Test
    void shouldReturnPushChannel() {
        assertEquals(NotificationChannel.PUSH, pushNotification.getChannel());
    }

    @Test
    void shouldSendNotificationWithoutErrors() {
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .name("Test User")
                .build();

        // Só garantimos que o método roda sem lançar exceção
        pushNotification.send(user, "Hello World!", "SPORTS");
    }
}
