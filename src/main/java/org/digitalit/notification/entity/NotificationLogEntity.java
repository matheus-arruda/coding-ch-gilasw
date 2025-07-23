package org.digitalit.notification.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationLogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_name", nullable = false, length = 100)
  private String userName;

  @Column(name = "user_email", nullable = false, length = 150)
  private String userEmail;

  @Column(nullable = false, length = 20)
  private String channel;

  @Column(nullable = false, length = 500)
  private String message;

  @Column(nullable = false, length = 50)
  private String category;

  @Column(nullable = false)
  private LocalDateTime timestamp;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private UserEntity user;
}
