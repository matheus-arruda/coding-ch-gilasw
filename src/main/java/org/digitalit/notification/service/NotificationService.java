package org.digitalit.notification.service;

import java.util.List;
import org.digitalit.notification.dto.NotificationRequestDTO;
import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.repository.UserRepository;
import org.digitalit.notification.strategy.NotificationStrategy;
import org.digitalit.notification.strategy.NotificationStrategyFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

  private final UserRepository userRepository;
  private final LogService logService;
  private final NotificationStrategyFactory strategyFactory;

  public NotificationService(
      UserRepository userRepository,
      LogService logService,
      NotificationStrategyFactory strategyFactory) {
    this.userRepository = userRepository;
    this.logService = logService;
    this.strategyFactory = strategyFactory;
  }

  public void sendNotification(NotificationRequestDTO request) {
    List<UserEntity> users = userRepository.findAll();

    for (UserEntity user : users) {
      System.out.println("Checking user: " + user.getName());
      System.out.println("Requested category: " + request.getCategory());

      boolean subscribed =
          user.getSubscribedCategories().stream()
              .map(CategoryEntity::getName)
              .anyMatch(catName -> catName.equalsIgnoreCase(request.getCategory()));

      if (!subscribed) continue;

      user.getChannels()
          .forEach(
              channel -> {
                NotificationStrategy strategy = strategyFactory.getStrategy(channel);
                strategy.send(user, request.getMessage(), request.getCategory());

                logService.saveLog(user, channel, request.getMessage(), request.getCategory());
              });
    }
  }
}
