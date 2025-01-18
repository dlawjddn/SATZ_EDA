package satz.event.satzeda.store.controller;

import org.springframework.web.bind.annotation.*;
import satz.event.satzeda.store.domain.Store;
import satz.event.satzeda.store.dto.request.CreateStoreDto;
import satz.event.satzeda.store.dto.request.UpdateStoreInfoDto;
import satz.event.satzeda.store.service.StoreService;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }
    @PostMapping
    public Store createStore(@RequestBody CreateStoreDto createStoreDto) {
        return storeService.createStore(createStoreDto);
    }

    @GetMapping
    public List<Store> getAllStoresByType(@RequestParam(name = "type") String type) {
        return storeService.getAllStoreByType(type);
    }

    @PatchMapping
    public void updateStoreInfo(@RequestBody UpdateStoreInfoDto storeInfoDto) {
        storeService.updateStoreInfo(storeInfoDto);
    }

    @PatchMapping("/open")
    public void updateStoreOpen(@RequestParam(name = "id") Long storeId) {
        storeService.updateStoreOpen(storeId);
    }

    @PatchMapping("/close")
    public void updateStoreClose(@RequestParam(name = "id") Long storeId) {
        storeService.updateStoreClosed(storeId);
    }
}
