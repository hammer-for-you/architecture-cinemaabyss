package ru.hammerforyou.events.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Maxim Nikolsky
 */
@RestController
public class HealthController {
    @GetMapping("/api/events/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(new HealthResponse(true));
    }

    public record HealthResponse(
            boolean status
    ) {}
}
