package satz.event.satzeda.tradehistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import satz.event.satzeda.tradehistory.domain.TradeHistory;
import satz.event.satzeda.user.domain.User;

import java.util.List;
import java.util.UUID;

public interface TradeHistoryRepository extends JpaRepository<TradeHistory, UUID> {
    List<TradeHistory> findAllByUser(User user);
}
