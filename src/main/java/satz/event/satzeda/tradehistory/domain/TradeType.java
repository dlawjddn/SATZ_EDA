package satz.event.satzeda.tradehistory.domain;

public enum TradeType {
    DEPOSIT("입금"),
    PURCHASE("구매"),
    CANCEL("취소")
    ;

    private final String type;

    TradeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
