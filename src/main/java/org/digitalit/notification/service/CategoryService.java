package org.digitalit.notification.service;

import java.util.List;
import org.digitalit.notification.entity.CategoryEntity;
import org.digitalit.notification.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  private final CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public List<CategoryEntity> getAll() {
    return repository.findAll();
  }
}
