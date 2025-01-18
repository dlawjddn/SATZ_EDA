package satz.event.satzeda.tradehistory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import satz.event.satzeda.tradehistory.domain.TradeHistory;
import satz.event.satzeda.tradehistory.dto.request.UserInfoDto;
import satz.event.satzeda.tradehistory.service.TradeHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/trade-histories")
public class TradeHistoryController {
    private final TradeHistoryService tradeHistoryService;

    public TradeHistoryController(TradeHistoryService tradeHistoryService) {
        this.tradeHistoryService = tradeHistoryService;
    }

    @GetMapping
    public List<TradeHistory> getTradeHistories(@RequestBody UserInfoDto userInfoDto) {
        return tradeHistoryService.getAllMyTradeHistories(userInfoDto);
    }
}
