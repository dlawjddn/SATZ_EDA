package satz.event.satzeda.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import satz.event.satzeda.food.domain.Food;
import satz.event.satzeda.store.domain.Store;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findALlByStore(Store store);

    @Modifying
    @Query("update Food f set f.isAvailable = true where f.id = :foodId")
    void updateFoodReady(Long foodId);

    @Modifying
    @Query("update Food f set f.isAvailable = false where f.id = :foodId")
    void updateFoodSoldOut(Long foodId);
}
