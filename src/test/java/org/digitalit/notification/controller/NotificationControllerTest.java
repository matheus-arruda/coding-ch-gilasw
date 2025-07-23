package org.digitalit.notification.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.digitalit.notification.dto.NotificationRequestDTO;
import org.digitalit.notification.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class NotificationControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCallServiceAndReturnOk() throws Exception {
        // Arrange
        NotificationService mockService = Mockito.mock(NotificationService.class);
        NotificationController controller = new NotificationController(mockService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        NotificationRequestDTO requestDTO = NotificationRequestDTO.builder()
                .category("SPORTS")
                .message("Hello, world!")
                .build();

        // Act & Assert
        mockMvc.perform(post("/notifications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk());

        verify(mockService, times(1)).sendNotification(requestDTO);
    }
}
