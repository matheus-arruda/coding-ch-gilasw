package org.digitalit.notification.repository;

import org.digitalit.notification.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {}
