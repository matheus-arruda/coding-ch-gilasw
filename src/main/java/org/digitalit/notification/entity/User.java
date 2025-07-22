package org.digitalit.notification.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.digitalit.notification.entity.enums.Category;
import org.digitalit.notification.entity.enums.NotificationChannel;

import java.util.List;

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    private String email;
    private String phone;

    @ElementCollection
    private List<Category> subscribedCategories;

    @ElementCollection
    private List<NotificationChannel> channels;
}
