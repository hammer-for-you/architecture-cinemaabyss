package ru.hammerforyou.events.event.model;

import java.time.LocalDateTime;

/**
 * @author Maxim Nikolsky
 */
public record Event(
        String id,
        EventType type,
        LocalDateTime timestamp,
        Object payload
) {
}
