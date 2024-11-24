package com.mop.dictionaryApp.controller;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class CustomErrorController implements ErrorController {

    private final ErrorAttributes errorAttributes;

    public CustomErrorController(ErrorAttributes errorAttributes) {
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/error")
    public ResponseEntity<Object> handleError(HttpServletRequest request) {
        System.out.println("CustomErrorController is handling the error.");

        Map<String, Object> errorDetails = errorAttributes.getErrorAttributes(
                new ServletWebRequest(request),
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE)
        );

        Map<String, Object> response = Map.of(
                "timestamp", LocalDateTime.now(),
                "message", errorDetails.getOrDefault("message", "An unexpected error occurred"),
                "description", errorDetails.getOrDefault("error", "No specific details available"),
                "status", errorDetails.get("status"),
                "path", errorDetails.get("path")
        );

        return ResponseEntity.status((int) errorDetails.get("status"))
                .header("Content-Type", "application/json")
                .body(response);
    }
}
