package org.digitalit.notification.entity;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryEntity {

  @Id private Long id;

  @Column(nullable = false, unique = true, length = 50)
  private String code;

  @Column(nullable = false, length = 100)
  private String name;
}
