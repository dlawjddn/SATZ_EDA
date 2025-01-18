package satz.event.satzeda.food.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import satz.event.satzeda.food.domain.Food;
import satz.event.satzeda.food.dto.request.CreateFoodDto;
import satz.event.satzeda.food.repository.FoodRepository;
import satz.event.satzeda.store.domain.Store;
import satz.event.satzeda.store.repository.StoreRepository;
import satz.event.satzeda.user.domain.User;
import satz.event.satzeda.user.repository.UserRepository;

import java.util.List;

@Service
public class FoodService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final FoodRepository foodRepository;

    public FoodService(UserRepository userRepository, StoreRepository storeRepository, FoodRepository foodRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.foodRepository = foodRepository;
    }
    @Transactional
    public Food createFood(CreateFoodDto createFoodDto) {
        User user = userRepository.findById(createFoodDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("회원 아이디 없음"));

        Store store = storeRepository.findById(createFoodDto.storeId())
                .orElseThrow(() -> new IllegalArgumentException("가게 아이디 틀림"));

        if (!store.getOwner().equals(user))
            throw new IllegalArgumentException("가게 주인이 아님");

        return foodRepository.save(
                new Food.Builder()
                        .name(createFoodDto.name())
                        .price(createFoodDto.price())
                        .introduce(createFoodDto.introduce())
                        .store(store)
                        .builder()
        );
    }

    public List<Food> getAllFoodsByStore(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 아이디 틀림"));

        return foodRepository.findALlByStore(store);
    }

    @Transactional
    public void soldOut(Long foodId) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("음식 아이디 틀림"));

        foodRepository.updateFoodSoldOut(food.getId());
    }

    @Transactional
    public void readyForFood(Long foodId) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("음식 아이디 틀림"));

        foodRepository.updateFoodReady(food.getId());
    }
}
