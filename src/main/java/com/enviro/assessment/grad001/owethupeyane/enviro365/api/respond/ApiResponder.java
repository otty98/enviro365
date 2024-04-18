package com.enviro.assessment.grad001.owethupeyane.enviro365.api.respond;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * Utility class to handle API responses.
 */
public class ApiResponder {

    /**
     * Generates a ResponseEntity with the provided status and response body.
     * @param status HTTP status code.
     * @param response Response body.
     * @return ResponseEntity object.
     */
    protected ResponseEntity<Object> getResponse(HttpStatus status, Object response) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    /**
     * Generates a ResponseEntity for error responses with the provided status and response body.
     * @param status HTTP status code.
     * @param response Error response body.
     * @return ResponseEntity object for error response.
     */
    protected ResponseEntity<Object> getResponseError(HttpStatus status, Object response) {
        Map<String, Object> error = Map.of("Error", response);
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(error);
    }
}
