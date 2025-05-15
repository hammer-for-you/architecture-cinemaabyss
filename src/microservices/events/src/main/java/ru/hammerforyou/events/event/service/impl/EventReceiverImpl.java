package ru.hammerforyou.events.event.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.hammerforyou.events.event.model.Event;
import ru.hammerforyou.events.event.service.EventReceiver;
import ru.hammerforyou.events.movies.MovieEvent;
import ru.hammerforyou.events.payments.PaymentEvent;
import ru.hammerforyou.events.users.UserEvent;

/**
 * @author Maxim Nikolsky
 */
@Component
class EventReceiverImpl implements EventReceiver {
    private static final Logger logger = LoggerFactory.getLogger(EventReceiverImpl.class);

    @Override
    @KafkaListener(topics = "user-events")
    public void receiveUserEvent(@Payload Event event) {
        logger.info("User event received: {}", event);
    }

    @Override
    @KafkaListener(topics = "movie-events")
    public void receiveMovieEvent(@Payload Event event) {
        logger.info("Movie event received: {}", event);
    }

    @Override
    @KafkaListener(topics = "payment-events")
    public void receivePaymentEvent(@Payload Event event) {
        logger.info("Payment event received: {}", event);
    }
}
