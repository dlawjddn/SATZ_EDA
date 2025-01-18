package satz.event.satzeda.user.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import satz.event.satzeda.user.dto.request.UpdateUserInfoDto;

@Entity
@DynamicUpdate
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname")
    private String nickname;

    @Column(name = "address")
    private String address;

    @Column(name = "point")
    private Integer point;

    @Column(name = "reward")
    private Integer reward;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private ERole role;

    // JPA용 protected 생성자
    protected User() {}

    // Builder 패턴 적용 생성자
    private User(Builder builder) {
        this.nickname = builder.nickname;
        this.address = builder.address;
        this.point = builder.point;
        this.reward = builder.reward;
        this.role = builder.role;
    }

    public static class Builder {
        private String nickname;
        private String address;
        private Integer point = 0;
        private Integer reward = 0;
        private ERole role = ERole.USER;

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

        public Builder role(ERole role) {
            this.role = role;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public Long getId() {
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

    public ERole getRole() {
        return role;
    }

    public void updateUserInfo(UpdateUserInfoDto updateUserInfoDto) {
        this.nickname = updateUserInfoDto.nickname();
        this.address = updateUserInfoDto.address();
    }

    public void addPoint(Integer amount) {
        this.point += amount;
    }
}
