package ru.hammerforyou.events.movies;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class MovieController {
    private final EventSender sender;

    public MovieController(EventSender sender) {
        this.sender = sender;
    }

    @PostMapping("/api/events/movie")
    public ResponseEntity<EventResponse> userEvent(
            @Valid @RequestBody MovieEvent event
    ) {
        var response = sender.send(newEvent(event));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    private Event newEvent(MovieEvent event) {
        return new Event(
                "movie-%d-%s".formatted(event.movieId(), event.action()),
                EventType.movie,
                LocalDateTime.now(),
                event
        );
    }
}
