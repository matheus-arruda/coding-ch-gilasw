package org.digitalit.notification.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class CategoryControllerTest {

  @Test
  void shouldReturnAllCategories() throws Exception {
    // Arrange
    CategoryService mockService = Mockito.mock(CategoryService.class);
    CategoryController controller = new CategoryController(mockService);

    List<CategoryEntity> categories =
        List.of(
            CategoryEntity.builder().id(1L).code("SPORTS").name("Sports").build(),
            CategoryEntity.builder().id(2L).code("FINANCE").name("Finance").build());

    when(mockService.getAll()).thenReturn(categories);

    MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    // Act & Assert
    mockMvc
        .perform(get("/categories").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2))
        .andExpect(jsonPath("$[0].code").value("SPORTS"))
        .andExpect(jsonPath("$[1].code").value("FINANCE"));
  }
}
