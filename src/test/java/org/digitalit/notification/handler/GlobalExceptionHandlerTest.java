package org.digitalit.notification.handler;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldHandleValidationErrors() throws NoSuchMethodException {
        // Mock FieldError
        FieldError fieldError = new FieldError("object", "message", "Message must be between 3 and 100 characters");

        // Mock BindingResult
        BindingResult bindingResult = mock(BindingResult.class);
        when(bindingResult.getFieldErrors()).thenReturn(List.of(fieldError));

        // Create MethodArgumentNotValidException manually
        Method method = this.getClass().getDeclaredMethod("dummyMethod");
        MethodArgumentNotValidException exception =
                new MethodArgumentNotValidException(null, bindingResult);

        // Call handler
        ResponseEntity<Map<String, String>> response = handler.handleValidationErrors(exception);

        // Assertions
        assertEquals(400, response.getStatusCodeValue());
        assertTrue(response.getBody().containsKey("message"));
        assertEquals("Message must be between 3 and 100 characters", response.getBody().get("message"));
    }

    // Dummy method just for reflection
    private void dummyMethod() {}

    @Test
    void shouldHandleGenericException() {
        Exception ex = new RuntimeException("Something went wrong");

        ResponseEntity<Map<String, String>> response = handler.handleGeneric(ex);

        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Something went wrong", response.getBody().get("error"));
    }
}
