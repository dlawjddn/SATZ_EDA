package satz.event.satzeda.user.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import satz.event.satzeda.tradehistory.domain.TradeType;
import satz.event.satzeda.user.domain.ERole;
import satz.event.satzeda.user.domain.User;
import satz.event.satzeda.user.dto.request.ChangePointDto;
import satz.event.satzeda.user.dto.request.CreateUserInfoDto;
import satz.event.satzeda.user.dto.request.UpdateUserInfoDto;
import satz.event.satzeda.user.event.event.DepositEvent;
import satz.event.satzeda.user.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ApplicationEventPublisher eventPublisher;
    public UserService(UserRepository userRepository, ApplicationEventPublisher eventPublisher) {
        this.userRepository = userRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public User createUser(CreateUserInfoDto userInfo) {
        return userRepository.save(new User.Builder()
                        .nickname(userInfo.nickname())
                        .address(userInfo.address())
                        .role(ERole.converter(userInfo.role()))
                .build()
        );
    }

    public User getUserInfo(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 회원 아이디임 ㅅㄱ~"));
    }

    @Transactional
    public void updateUserInfo(UpdateUserInfoDto userInfo) {
        User user = userRepository.findById(userInfo.userId())
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 회원 아이디임 ㅅㄱ~"));

        user.updateUserInfo(userInfo);
    }

    @Transactional
    public void addPoint(ChangePointDto changePointDto) {
        User user = userRepository.findById(changePointDto.userId())
                .orElseThrow(() -> new IllegalArgumentException("찾을 수 없는 회원 아이디임 ㅅㄱ~"));

        user.addPoint(changePointDto.amount());
        // 거래 내역 event 발행하기
        eventPublisher.publishEvent(new DepositEvent.Builder()
                        .user(user)
                        .type(TradeType.DEPOSIT)
                        .amount(changePointDto.amount())
                .build()
        );
    }
}
