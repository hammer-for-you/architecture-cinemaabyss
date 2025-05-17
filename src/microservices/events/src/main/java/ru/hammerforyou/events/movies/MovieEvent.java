package ru.hammerforyou.events.movies;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import ru.hammerforyou.events.event.model.EventPayload;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Maxim Nikolsky
 */
public record MovieEvent(
    @NotNull @JsonProperty("movie_id") Integer movieId,
    @NotBlank String title,
    @NotBlank String action,
    @JsonProperty("user_id") Integer userId,
    Double rating,
    List<String> genres,
    String description
) implements EventPayload {
    public MovieEvent {
        if (genres == null) {
            genres = new ArrayList<>();
        }
    }
}
