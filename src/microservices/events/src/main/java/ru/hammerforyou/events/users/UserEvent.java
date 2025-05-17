package ru.hammerforyou.events.users;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.hammerforyou.events.event.model.EventPayload;

import java.time.LocalDateTime;

/**
 * @author Maxim Nikolsky
 */
public record UserEvent(
        @NotNull @JsonProperty("user_id") Integer userId,
        String username,
        String email,
        @NotBlank String action,
        @NotNull LocalDateTime timestamp
) implements EventPayload {
}
