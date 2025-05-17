package ru.hammerforyou.events.event.service;

import ru.hammerforyou.events.event.model.Event;
import ru.hammerforyou.events.event.model.EventPayload;
import ru.hammerforyou.events.event.model.EventResponse;

/**
 * @author Maxim Nikolsky
 */
public interface EventSender {
    EventResponse send(Event event);
}
