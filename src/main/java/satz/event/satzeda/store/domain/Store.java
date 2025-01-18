package satz.event.satzeda.store.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.transaction.annotation.Transactional;
import satz.event.satzeda.store.dto.request.UpdateStoreInfoDto;
import satz.event.satzeda.user.domain.User;

@Entity
@DynamicUpdate
@Table(name = "stores")
public class Store {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "min_price", nullable = false)
    private Integer minPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EStoreType type;

    @Column(name = "active", nullable = false)
    private Boolean isActive;

    protected Store() {}

    private Store(Builder builder) {
        this.owner = builder.owner;
        this.name = builder.name;
        this.address = builder.address;
        this.introduction = builder.introduction;
        this.minPrice = builder.minPrice;
        this.type = builder.type;
        this.isActive = builder.isActive;
    }

    public static class Builder {
        private User owner;
        private String name;
        private String address;
        private String introduction;
        private Integer minPrice;
        private EStoreType type;
        private Boolean isActive = Boolean.FALSE;

        public Builder owner(User owner) {
            this.owner = owner;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder introduction(String introduction) {
            this.introduction = introduction;
            return this;
        }

        public Builder minPrice(Integer minPrice) {
            this.minPrice = minPrice;
            return this;
        }

        public Builder type(EStoreType type) {
            this.type = type;
            return this;
        }

        public Builder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Store build() {
            return new Store(this);
        }
    }
    public Long getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getAddress() {
        return address;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public EStoreType getType() {
        return type;
    }

    public Boolean getIsActive() {
        return isActive;
    }
    @Transactional
    public void updateActiveState() {
        this.isActive = !this.isActive;
    }

    @Transactional
    public void updateStoreInfo(UpdateStoreInfoDto storeInfoDto) {
        this.address = storeInfoDto.address();
        this.introduction = storeInfoDto.address();
        this.minPrice = storeInfoDto.minPrice();
        this.name = storeInfoDto.name();
    }
}
