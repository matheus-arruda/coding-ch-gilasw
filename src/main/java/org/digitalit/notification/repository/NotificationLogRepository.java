package org.digitalit.notification.repository;

import org.digitalit.notification.entity.NotificationLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationLogRepository extends JpaRepository<NotificationLogEntity, Long> {}
