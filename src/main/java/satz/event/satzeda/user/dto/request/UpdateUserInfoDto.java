package satz.event.satzeda.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record UpdateUserInfoDto(
        @JsonProperty("user_id")
        UUID userId,

        @JsonProperty("nickname")
        String nickname,

        @JsonProperty("address")
        String address
) {
}
