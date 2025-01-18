package satz.event.satzeda.tradehistory.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record UserInfoDto(
        @JsonProperty("user_id")
        UUID userId

) {
}
