package satz.event.satzeda.store.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateStoreInfoDto(
        @JsonProperty("store_id")
        Long storeId,

        @JsonProperty("name")
        String name,

        @JsonProperty("address")
        String address,

        @JsonProperty("introduction")
        String introduction,

        @JsonProperty("min_price")
        Integer minPrice
) {
}
