package org.digitalit.notification.service;

import org.digitalit.notification.dto.NotificationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationStrategyFactory strategyFactory;


    public void sendNotification(NotificationRequestDTO request) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (!user.getSubscribedCategories().contains(request.getCategory())) continue;

            for (NotificationChannel channel : user.getChannels()) {
                NotificationStrategy strategy = strategyFactory.getStrategy(channel);
                strategy.send(user, request.getMessage(), request.getCategory());
            }
        }
    }
}
