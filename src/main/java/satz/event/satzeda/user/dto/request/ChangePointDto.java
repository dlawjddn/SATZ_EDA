package satz.event.satzeda.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record ChangePointDto(
        @JsonProperty("user_id")
        UUID userId,

        @JsonProperty("amount")
        Integer amount
) {
}
