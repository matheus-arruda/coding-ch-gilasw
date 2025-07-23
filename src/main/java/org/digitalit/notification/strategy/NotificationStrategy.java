package org.digitalit.notification.strategy;

import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;

public interface NotificationStrategy {
  void send(UserEntity user, String message, String category);

  NotificationChannel getChannel();
}
