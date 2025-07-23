package org.digitalit.notification.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationRequestDTO {
  private String message;
  private String category;
  private long userId;
}
