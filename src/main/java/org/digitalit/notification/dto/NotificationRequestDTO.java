package org.digitalit.notification.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NotificationRequestDTO {
  @NotBlank(message = "Category is required")
  private String category;

  @NotBlank(message = "Message is required")
  @Size(min = 3, max = 100, message = "Message must be between 3 and 100 characters")
  private String message;

  private long userId;
}
