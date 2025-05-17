package ru.hammerforyou.events.event.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.hammerforyou.events.event.model.Event;
import ru.hammerforyou.events.event.model.EventPayload;
import ru.hammerforyou.events.event.model.EventResponse;
import ru.hammerforyou.events.event.service.EventSender;

import java.util.concurrent.ExecutionException;

/**
 * @author Maxim Nikolsky
 */
@Component
class EventSenderImpl implements EventSender {
    private static final Logger logger = LoggerFactory.getLogger(EventSenderImpl.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public EventSenderImpl(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public EventResponse send(Event event) {
        try {
            var result = kafkaTemplate.send(selectTopic(event), event).get();
            return new EventResponse(
                    "success",
                    result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset(),
                    event
            );
        } catch (InterruptedException | ExecutionException e) {
            logger.error("Event {} failed to send", event, e);
            throw new RuntimeException(e);
        }
    }

    private <T extends EventPayload> String selectTopic(Event event) {
        return switch (event.type()) {
            case user -> "user-events";
            case movie -> "movie-events";
            case payment -> "payment-events";
        };
    }
}
