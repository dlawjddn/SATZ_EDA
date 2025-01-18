package satz.event.satzeda.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import satz.event.satzeda.store.domain.EStoreType;
import satz.event.satzeda.store.domain.Store;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("select s from Store s where s.type = :type and s.isActive = true")
    List<Store> findAllByType(EStoreType type);
}
