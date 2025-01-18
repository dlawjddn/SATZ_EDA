package satz.event.satzeda.store.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import satz.event.satzeda.store.domain.EStoreType;
import satz.event.satzeda.store.domain.Store;
import satz.event.satzeda.store.dto.request.CreateStoreDto;
import satz.event.satzeda.store.dto.request.UpdateStoreInfoDto;
import satz.event.satzeda.store.event.event.RefreshFoodStateEvent;
import satz.event.satzeda.store.repository.StoreRepository;
import satz.event.satzeda.user.domain.ERole;
import satz.event.satzeda.user.domain.User;
import satz.event.satzeda.user.repository.UserRepository;

import java.util.List;

@Service
public class StoreService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final ApplicationEventPublisher eventPublisher;

    public StoreService(UserRepository userRepository, StoreRepository storeRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Store createStore(CreateStoreDto createStoreDto) {
        User owner = userRepository.findById(createStoreDto.ownerId())
                .orElseThrow(() -> new IllegalArgumentException("회원 아이디 틀림"));

        if (!owner.getRole().equals(ERole.OWNER))
            throw new IllegalArgumentException("사장님 계정이 아님");

        return storeRepository.save(
                new Store.Builder()
                        .owner(owner)
                        .name(createStoreDto.name())
                        .address(createStoreDto.address())
                        .introduction(createStoreDto.introduction())
                        .type(EStoreType.convert(createStoreDto.type()))
                        .minPrice(createStoreDto.minPrice())
                        .build()
        );
    }

    public List<Store> getAllStoreByType(String type) {
        return storeRepository.findAllByType(EStoreType.convert(type));
    }

    public void updateStoreInfo(UpdateStoreInfoDto updateStoreInfo) {
        Store store = storeRepository.findById(updateStoreInfo.storeId())
                .orElseThrow(() -> new IllegalArgumentException("가게 아이디 불일치"));

        store.updateStoreInfo(updateStoreInfo);
    }

    @Transactional
    public void updateStoreOpen(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 아이디 불일치"));

        storeRepository.updateStoreOpen(store.getId());

        List<Long> foodIds = store.getFoods().stream()
                .map(food -> food.getId())
                .toList();
        // 음식 매진 상태 리프레시
        eventPublisher.publishEvent(new RefreshFoodStateEvent(foodIds));
    }

    @Transactional
    public void updateStoreClosed(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 아이디 불일치"));

        storeRepository.updateStoreClose(store.getId());
    }


}
