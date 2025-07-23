package org.digitalit.notification.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SmsNotificationTest {

    private SmsNotification smsNotification;

    @BeforeEach
    void setUp() {
        smsNotification = new SmsNotification();
    }

    @Test
    void shouldReturnSmsChannel() {
        assertEquals(NotificationChannel.SMS, smsNotification.getChannel());
    }

    @Test
    void shouldSendNotificationWithoutErrors() {
        UserEntity user = UserEntity.builder()
                .email("test@example.com")
                .name("Test User")
                .build();

        smsNotification.send(user, "This is a test SMS", "FINANCE");
    }
}
