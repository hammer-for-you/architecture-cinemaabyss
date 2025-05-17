package ru.hammerforyou.events.payments;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.hammerforyou.events.event.model.EventPayload;

import java.time.LocalDateTime;

/**
 * @author Maxim Nikolsky
 */
public record PaymentEvent(
        @NotNull @JsonProperty("payment_id") Integer paymentId,
        @NotNull @JsonProperty("user_id") Integer userId,
        @NotNull Double amount,
        @NotBlank String status,
        @NotNull LocalDateTime timestamp,
        @JsonProperty("method_type") String methodType
) implements EventPayload {
}
