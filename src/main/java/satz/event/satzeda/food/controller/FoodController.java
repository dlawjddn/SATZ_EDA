package satz.event.satzeda.food.controller;

import org.springframework.web.bind.annotation.*;
import satz.event.satzeda.food.domain.Food;
import satz.event.satzeda.food.dto.request.CreateFoodDto;
import satz.event.satzeda.food.service.FoodService;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping
    public Food createFood(@RequestBody CreateFoodDto createFoodDto) {
        return foodService.createFood(createFoodDto);
    }

    @GetMapping
    public List<Food> getAllFoodsByStore(@RequestParam(name = "store_id") Long storeId) {
        return foodService.getAllFoodsByStore(storeId);
    }

    @PatchMapping
    public void soldOut(@RequestParam(name = "food_id") Long foodId) {
        foodService.soldOut(foodId);
    }

}
