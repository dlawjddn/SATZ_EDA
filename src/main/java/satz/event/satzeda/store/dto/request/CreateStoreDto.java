package satz.event.satzeda.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateStoreDto(
        @JsonProperty("user_id")
        Long ownerId,

        @JsonProperty("name")
        String name,

        @JsonProperty("address")
        String address,

        @JsonProperty("introduction")
        String introduction,

        @JsonProperty("min_price")
        Integer minPrice,

        @JsonProperty("store_type")
        String type
) {
}
