package satz.event.satzeda.food.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.transaction.annotation.Transactional;
import satz.event.satzeda.store.domain.Store;

@Entity
@DynamicUpdate
@Table(name = "foods")
public class Food {
    @Id
    @Column(name = "food_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    protected Food() {}

    private Food(Builder builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.introduce = builder.introduce;
        this.isAvailable = builder.isAvailable;
        this.store = builder.store;
    }

    public static class Builder {
        private String name;
        private Integer price;
        private String introduce;
        private Boolean isAvailable = Boolean.TRUE;
        private Store store;

        public Builder name(String name) {
            this.name = name;
            return  this;
        }

        public Builder price(Integer price) {
            this.price = price;
            return this;
        }

        public Builder introduce(String introduce) {
            this.introduce = introduce;
            return this;
        }

        public Builder isAvailable(Boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Builder store(Store store) {
            this.store = store;
            return this;
        }

        public Food builder() {
            return new Food(this);
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getIntroduce() {
        return introduce;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Store getStore() {
        return store;
    }

    @Transactional
    public void soldOut() {
        this.isAvailable = Boolean.FALSE;
    }

    @Transactional
    public void available() {
        this.isAvailable = Boolean.TRUE;
    }
}
