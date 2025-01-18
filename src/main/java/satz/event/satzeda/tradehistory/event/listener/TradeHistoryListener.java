package satz.event.satzeda.tradehistory.event.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import satz.event.satzeda.tradehistory.domain.TradeHistory;
import satz.event.satzeda.tradehistory.domain.TradeType;
import satz.event.satzeda.tradehistory.repository.TradeHistoryRepository;
import satz.event.satzeda.user.event.event.DepositEvent;

@Component
public class TradeHistoryListener {
    private static final Logger log = LoggerFactory.getLogger(TradeHistoryListener.class);
    private final TradeHistoryRepository tradeHistoryRepository;

    public TradeHistoryListener(TradeHistoryRepository tradeHistoryRepository) {
        this.tradeHistoryRepository = tradeHistoryRepository;
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void deposit(DepositEvent depositEvent) {
        log.info("입금발생!!! " + depositEvent.getAmount().toString());

        tradeHistoryRepository.save(
                new TradeHistory.Builder()
                        .user(depositEvent.getUser())
                        .tradeType(TradeType.DEPOSIT)
                        .amount(depositEvent.getAmount())
                        .builder()
        );

    }
}
