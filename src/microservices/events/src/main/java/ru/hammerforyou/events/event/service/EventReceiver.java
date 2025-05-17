package ru.hammerforyou.events.event.service;

import ru.hammerforyou.events.event.model.Event;

/**
 * @author Maxim Nikolsky
 */
public interface EventReceiver {
    void receiveUserEvent(Event event);

    void receiveMovieEvent(Event event);

    void receivePaymentEvent(Event event);
}
