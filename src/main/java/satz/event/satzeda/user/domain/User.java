package satz.event.satzeda.user.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import satz.event.satzeda.user.dto.request.UpdateUserInfoDto;

import java.util.UUID;

@Entity
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "address")
    private String address;

    @Column(name = "point")
    private Integer point;

    @Column(name = "reward")
    private Integer reward;

    // JPA용 protected 생성자
    protected User() {}

    // Builder 패턴 적용 생성자
    private User(Builder builder) {
        this.nickname = builder.nickname;
        this.address = builder.address;
        this.point = builder.point;
        this.reward = builder.reward;
    }

    public static class Builder {
        private String nickname;
        private String address;
        private Integer point = 0;
        private Integer reward = 0;

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder point(Integer point) {
            this.point = point;
            return this;
        }

        public Builder reward(Integer reward) {
            this.reward = reward;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public Integer getPoint() {
        return point;
    }

    public Integer getReward() {
        return reward;
    }

    public void updateUserInfo(UpdateUserInfoDto updateUserInfoDto) {
        this.nickname = updateUserInfoDto.nickname();
        this.address = updateUserInfoDto.address();
    }

    public void addPoint(Integer amount) {
        this.point += amount;
    }
}
