package org.digitalit.notification.config;

import java.util.Set;
import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.entity.enums.NotificationChannel;
import org.digitalit.notification.repository.CategoryRepository;
import org.digitalit.notification.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbSeedConfig {

  @Bean
  public CommandLineRunner seedUsers(
      UserRepository userRepository, CategoryRepository categoryRepository) {
    return args -> {
      if (categoryRepository.count() == 0) {
        categoryRepository.saveAll(
            Set.of(
                CategoryEntity.builder().id(1L).code("SPORTS").name("Sports").build(),
                CategoryEntity.builder().id(2L).code("FINANCE").name("Finance").build(),
                CategoryEntity.builder().id(3L).code("MOVIES").name("Movies").build()));
      }

      if (userRepository.count() == 0) {
        Set<CategoryEntity> categories =
            Set.of(
                categoryRepository.findByCode("SPORTS"), categoryRepository.findByCode("FINANCE"));

        UserEntity user =
            UserEntity.builder()
                .id(1L)
                .name("Matheus Arruda")
                .email("matheush13@gmail.com")
                .phone("+1234567890")
                .subscribedCategories(categories)
                .channels(Set.of(NotificationChannel.EMAIL, NotificationChannel.SMS))
                .build();

        userRepository.save(user);
      }
    };
  }
}
