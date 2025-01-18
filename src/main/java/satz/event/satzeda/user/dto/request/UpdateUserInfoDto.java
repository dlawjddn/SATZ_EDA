package satz.event.satzeda.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserInfoDto(
        @JsonProperty("user_id")
        Long userId,

        @JsonProperty("nickname")
        String nickname,

        @JsonProperty("address")
        String address
) {
}
