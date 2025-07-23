package org.digitalit.notification.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;
import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class CategoryServiceTest {

  @Mock private CategoryRepository categoryRepository;

  @InjectMocks private CategoryService categoryService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void getAll_ShouldReturnListOfCategories() {
    // Given
    List<CategoryEntity> mockCategories =
        List.of(
            CategoryEntity.builder().id(1L).name("Sports").code("SPORTS").build(),
            CategoryEntity.builder().id(2L).name("Tech").code("TECH").build());
    when(categoryRepository.findAll()).thenReturn(mockCategories);

    // When
    List<CategoryEntity> result = categoryService.getAll();

    // Then
    assertThat(result).isEqualTo(mockCategories);
    verify(categoryRepository, times(1)).findAll();
  }
}
