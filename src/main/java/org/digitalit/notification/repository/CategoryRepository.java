package org.digitalit.notification.repository;

import org.digitalit.notification.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
  CategoryEntity findByCode(String code);
}
