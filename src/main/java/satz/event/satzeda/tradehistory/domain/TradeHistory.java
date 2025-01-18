package satz.event.satzeda.tradehistory.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import satz.event.satzeda.user.domain.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trade_histories")
public class TradeHistory {
    @Id
    @Column(name = "trade_history_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TradeType tradeType;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    protected TradeHistory() {}

    public TradeHistory(Builder builder) {
        this.user = builder.user;
        this.tradeType = builder.tradeType;
        this.amount = builder.amount;
        this.createdAt = LocalDateTime.now();
    }

    public static class Builder {
        private User user;
        private TradeType tradeType;
        private Integer amount;

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder tradeType(TradeType tradeType) {
            this.tradeType = tradeType;
            return this;
        }

        public Builder amount(Integer amount) {
            this.amount = amount;
            return this;
        }

        public TradeHistory builder() {
            return new TradeHistory(this);
        }
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public TradeType getTradeType() {
        return tradeType;
    }

    public Integer getAmount() {
        return amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
