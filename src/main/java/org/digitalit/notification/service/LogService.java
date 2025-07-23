package org.digitalit.notification.service;

import java.time.LocalDateTime;
import java.util.List;
import org.digitalit.notification.entity.NotificationLogEntity;
import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.digitalit.notification.repository.NotificationLogRepository;
import org.springframework.stereotype.Service;

@Service
public class LogService {
  private final NotificationLogRepository logRepository;

  public LogService(NotificationLogRepository logRepository) {
    this.logRepository = logRepository;
  }

  public void saveLog(
      UserEntity user, NotificationChannel channel, String message, String category) {
    NotificationLogEntity log =
        NotificationLogEntity.builder()
            .user(user)
            .userName(user.getName())
            .userEmail(user.getEmail())
            .channel(channel.name())
            .message(message)
            .category(category)
            .timestamp(LocalDateTime.now())
            .build();

    logRepository.save(log);
  }

  public List<NotificationLogEntity> getAllLogs() {
    return logRepository.findAll();
  }
}
