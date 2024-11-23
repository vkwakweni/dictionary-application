// Path: src/main/java/com/mop/dictionaryApp/aspect/ErrorHandlingAspect.java

package com.mop.dictionaryApp.aspect;

import com.mop.dictionaryApp.exception.ApiException;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ErrorHandlingAspect {

    @Around("within(@org.springframework.web.bind.annotation.RestController *)")
    public Object handleControllerErrors(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            return joinPoint.proceed(); // Proceed with the original method
        } catch (ApiException ex) {
            // Log the exception
            System.out.println("Aspect caught ApiException: " + ex.getErrorCode());
            throw ex; // Rethrow to let Spring handle it
        } catch (Exception ex) {
            // Log unexpected exceptions
            System.out.println("Aspect caught unexpected Exception: " + ex.getMessage());
            throw ex; // Rethrow generic exceptions
        }
    }

    /**
     * Constructs a structured error response and sets it as HTTP response.
     */
    private ResponseEntity<Object> buildErrorResponse(String message, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("message", message);
        response.put("status", status.value());
        response.put("error", status.getReasonPhrase());

        // Log the response for debugging
        System.out.println("Building HTTP response: " + response);

        // Return ResponseEntity to properly map to HTTP response
        return ResponseEntity.status(status)
                .header("Content-Type", "application/json")
                .body(response);
    }
}
