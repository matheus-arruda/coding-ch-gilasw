package org.digitalit.notification.controller;

import java.util.List;
import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
  private final CategoryService service;

  public CategoryController(CategoryService service) {
    this.service = service;
  }

  @GetMapping
  public List<CategoryEntity> getAll() {
    return service.getAll();
  }
}
