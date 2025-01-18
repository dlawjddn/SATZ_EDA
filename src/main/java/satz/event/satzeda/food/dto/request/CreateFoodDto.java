package satz.event.satzeda.food.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateFoodDto(
        @JsonProperty("user_id")
        Long userId,

        @JsonProperty("store_id")
        Long storeId,

        @JsonProperty("name")
        String name,

        @JsonProperty("price")
        Integer price,

        @JsonProperty("introduce")
        String introduce

) {
}
