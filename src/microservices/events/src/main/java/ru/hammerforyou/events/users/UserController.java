package ru.hammerforyou.events.users;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.hammerforyou.events.event.model.Event;
import ru.hammerforyou.events.event.model.EventResponse;
import ru.hammerforyou.events.event.model.EventType;
import ru.hammerforyou.events.event.service.EventSender;

import java.time.LocalDateTime;

/**
 * @author Maxim Nikolsky
 */
@RestController
@Validated
public class UserController {
    private final EventSender sender;

    public UserController(EventSender sender) {
        this.sender = sender;
    }

    @PostMapping("/api/events/user")
    public ResponseEntity<EventResponse> userEvent(
            @Valid @RequestBody UserEvent event
    ) {
        var response = sender.send(newEvent(event));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    private Event newEvent(UserEvent event) {
        return new Event(
                "user-%d-%s".formatted(event.userId(), event.action()),
                EventType.user,
                LocalDateTime.now(),
                event
        );
    }
}
