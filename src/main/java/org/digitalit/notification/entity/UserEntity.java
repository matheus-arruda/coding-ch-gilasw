package org.digitalit.notification.entity;

import jakarta.persistence.*;
import java.util.Set;
import lombok.*;
import org.digitalit.notification.entity.enums.NotificationChannel;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
  @Id private Long id;

  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 150, unique = true)
  private String email;

  @Column(length = 20)
  private String phone;

  @ManyToMany
  @JoinTable(
      name = "user_categories",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "category_id"))
  private Set<CategoryEntity> subscribedCategories;

  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_channels", joinColumns = @JoinColumn(name = "user_id"))
  @Enumerated(EnumType.STRING)
  @Column(name = "channel", length = 20)
  private Set<NotificationChannel> channels;
}
