package org.digitalit.notification.strategy;

import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.springframework.stereotype.Component;

@Component
public class EmailNotification implements NotificationStrategy {

  @Override
  public void send(UserEntity user, String message, String category) {
    System.out.printf("-> Email sent to %s: %s (%s)%n", user.getEmail(), message, category);
  }

  @Override
  public NotificationChannel getChannel() {
    return NotificationChannel.EMAIL;
  }
}
