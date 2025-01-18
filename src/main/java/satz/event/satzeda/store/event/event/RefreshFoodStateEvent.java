package satz.event.satzeda.store.event.event;

import satz.event.satzeda.store.domain.Store;

import java.util.List;

public record RefreshFoodStateEvent(
        List<Long> foodIds
) {
}
