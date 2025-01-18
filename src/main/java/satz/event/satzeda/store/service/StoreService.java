package satz.event.satzeda.store.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import satz.event.satzeda.store.domain.EStoreType;
import satz.event.satzeda.store.domain.Store;
import satz.event.satzeda.store.dto.request.CreateStoreDto;
import satz.event.satzeda.store.dto.request.UpdateStoreInfoDto;
import satz.event.satzeda.store.repository.StoreRepository;
import satz.event.satzeda.user.domain.ERole;
import satz.event.satzeda.user.domain.User;
import satz.event.satzeda.user.repository.UserRepository;

import java.util.List;

@Service
public class StoreService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    public StoreService(UserRepository userRepository, StoreRepository storeRepository) {
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
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

    public void updateStoreActive(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("가게 아이디 불일치"));

        store.updateActiveState();
    }
}
