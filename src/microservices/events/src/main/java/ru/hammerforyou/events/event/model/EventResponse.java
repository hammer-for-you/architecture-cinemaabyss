package ru.hammerforyou.events.event.model;

/**
 * @author Maxim Nikolsky
 */
public record EventResponse(
    String status,
    Integer partition,
    Long offset,
    Event event
) {
}
