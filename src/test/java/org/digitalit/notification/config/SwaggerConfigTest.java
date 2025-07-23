package org.digitalit.notification.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SwaggerConfigTest {

    @Test
    void shouldReturnConfiguredOpenAPI() {
        // Arrange
        SwaggerConfig config = new SwaggerConfig();

        // Act
        OpenAPI openAPI = config.customOpenAPI();

        // Assert
        assertNotNull(openAPI);
        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("Notification API", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertEquals("API to send notifications and to visualize the logs.", info.getDescription());
    }
}
