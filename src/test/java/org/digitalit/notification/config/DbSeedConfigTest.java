package org.digitalit.notification.config;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.entity.UserEntity;
import org.digitalit.notification.repository.CategoryRepository;
import org.digitalit.notification.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DbSeedConfigTest {

  private UserRepository userRepository;
  private CategoryRepository categoryRepository;
  private DbSeedConfig dbSeedConfig;

  @BeforeEach
  void setup() {
    userRepository = mock(UserRepository.class);
    categoryRepository = mock(CategoryRepository.class);
    dbSeedConfig = new DbSeedConfig();
  }

  @Test
  void shouldSeedUsersAndCategoriesWhenEmpty() throws Exception {
    // arrange
    when(categoryRepository.count()).thenReturn(0L);
    when(userRepository.count()).thenReturn(0L);

    CategoryEntity sports = CategoryEntity.builder().id(1L).code("SPORTS").name("Sports").build();
    CategoryEntity finance =
        CategoryEntity.builder().id(2L).code("FINANCE").name("Finance").build();

    when(categoryRepository.findByCode("SPORTS")).thenReturn(sports);
    when(categoryRepository.findByCode("FINANCE")).thenReturn(finance);

    // act
    dbSeedConfig.seedUsers(userRepository, categoryRepository).run();

    // assert
    verify(categoryRepository).saveAll(anySet());
    verify(userRepository).save(any(UserEntity.class));
  }

  @Test
  void shouldNotSeedIfAlreadyPopulated() throws Exception {
    when(categoryRepository.count()).thenReturn(3L);
    when(userRepository.count()).thenReturn(1L);

    dbSeedConfig.seedUsers(userRepository, categoryRepository).run();

    verify(categoryRepository, never()).saveAll(any());
    verify(userRepository, never()).save(any());
  }
}
