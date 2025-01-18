package satz.event.satzeda.user.event.event;

import satz.event.satzeda.tradehistory.domain.TradeType;
import satz.event.satzeda.user.domain.User;

public class DepositEvent {
    private final User user;
    private final TradeType type;
    private final Integer amount;

    private DepositEvent(Builder builder) {
        this.user = builder.user;
        this.type = builder.type;
        this.amount = builder.amount;
    }

    public static class Builder {
        private User user;
        private TradeType type;
        private Integer amount;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder type(TradeType type) {
            this.type = type;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public DepositEvent build() {
            return new DepositEvent(this);
        }
    }

    public User getUser() {
        return user;
    }

    public TradeType getType() {
        return type;
    }

    public Integer getAmount() {
        return amount;
    }
}
