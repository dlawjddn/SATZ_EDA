package satz.event.satzeda.food.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import satz.event.satzeda.food.domain.Food;
import satz.event.satzeda.food.repository.FoodRepository;
import satz.event.satzeda.store.event.event.RefreshFoodStateEvent;

@Component
public class FoodEventListener {

    public static final Logger log = LoggerFactory.getLogger(FoodEventListener.class);
    private final FoodRepository foodRepository;

    public FoodEventListener (FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void refreshFoodState(RefreshFoodStateEvent refreshEvent) {
        log.info("event 청취 확인");
        for(Long foodId : refreshEvent.foodIds()) {
            Food food = foodRepository.findById(foodId)
                    .orElseThrow(() -> new IllegalArgumentException("잘못된 음식 아이디"));

            foodRepository.updateFoodReady(foodId);
        }
        log.info("완료");
    }
}
