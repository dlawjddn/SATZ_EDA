package satz.event.satzeda.user.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateUserInfoDto(
        @JsonProperty("nickname")
        String nickname,
        @JsonProperty("address")
        String address,
        @JsonProperty("role")
        String role
) {
}
