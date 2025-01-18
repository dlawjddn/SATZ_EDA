package satz.event.satzeda.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ChangePointDto(
        @JsonProperty("user_id")
        Long userId,

        @JsonProperty("amount")
        Integer amount
) {
}
