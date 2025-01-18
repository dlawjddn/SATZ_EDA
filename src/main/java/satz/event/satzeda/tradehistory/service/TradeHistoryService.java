package satz.event.satzeda.tradehistory.service;

import org.springframework.stereotype.Service;
import satz.event.satzeda.tradehistory.domain.TradeHistory;
import satz.event.satzeda.tradehistory.dto.request.UserInfoDto;
import satz.event.satzeda.tradehistory.repository.TradeHistoryRepository;
import satz.event.satzeda.user.domain.User;
import satz.event.satzeda.user.repository.UserRepository;

import java.util.List;

@Service
public class TradeHistoryService {
    private final UserRepository userRepository;
    private final TradeHistoryRepository tradeHistoryRepository;

    public TradeHistoryService(UserRepository userRepository, TradeHistoryRepository tradeHistoryRepository) {
        this.userRepository = userRepository;
        this.tradeHistoryRepository = tradeHistoryRepository;
    }

    public List<TradeHistory> getAllMyTradeHistories(UserInfoDto userInfoDto) {
        User user = userRepository.findById(userInfoDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("아이디 없음"));
        return tradeHistoryRepository.findAllByUser(user);
    }
}
