package org.digitalit.notification.controller;

import java.util.List;
import org.digitalit.notification.entity.NotificationLogEntity;
import org.digitalit.notification.service.LogService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logs")
public class LogController {

  private final LogService service;

  public LogController(LogService service) {
    this.service = service;
  }

  @GetMapping
  public List<NotificationLogEntity> getAllLogs() {
    return service.getAllLogs();
  }
}
