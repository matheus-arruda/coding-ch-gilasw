package org.digitalit.notification.strategy;

import org.digitalit.notification.entity.User;
import org.digitalit.notification.entity.enums.Category;

public interface NotificationStrategy {
    void send(User user, String message, Category category);
}
