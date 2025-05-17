package ru.hammerforyou.events.payments;

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
public class PaymentController {
    private final EventSender sender;

    public PaymentController(EventSender sender) {
        this.sender = sender;
    }

    @PostMapping("/api/events/payment")
    public ResponseEntity<EventResponse> userEvent(
            @Valid @RequestBody PaymentEvent event
    ) {
        var response = sender.send(newEvent(event));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    private Event newEvent(PaymentEvent event) {
        return new Event(
                "payment-%d-%s".formatted(event.paymentId(), event.status()),
                EventType.payment,
                LocalDateTime.now(),
                event
        );
    }
}
