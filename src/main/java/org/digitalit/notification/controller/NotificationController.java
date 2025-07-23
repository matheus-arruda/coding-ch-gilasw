package org.digitalit.notification.controller;

import jakarta.validation.Valid;
import org.digitalit.notification.dto.NotificationRequestDTO;
import org.digitalit.notification.service.NotificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

  private final NotificationService service;

  public NotificationController(NotificationService service) {
    this.service = service;
  }

  @PostMapping
  public void send(@RequestBody @Valid NotificationRequestDTO dto) {
    service.sendNotification(dto);
  }
}
