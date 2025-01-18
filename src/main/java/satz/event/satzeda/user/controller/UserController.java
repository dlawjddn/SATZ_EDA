package satz.event.satzeda.user.controller;

import org.springframework.web.bind.annotation.*;
import satz.event.satzeda.user.domain.User;
import satz.event.satzeda.user.dto.request.ChangePointDto;
import satz.event.satzeda.user.dto.request.CreateUserInfoDto;
import satz.event.satzeda.user.dto.request.UpdateUserInfoDto;
import satz.event.satzeda.user.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserInfoDto createUserInfoDto) {
        return userService.createUser(createUserInfoDto);
    }

    @GetMapping
    public User getUserInfo(@RequestParam(name = "id") UUID userId) {
        return userService.getUserInfo(userId);
    }

    @PatchMapping
    public void updateUserInfo(@RequestBody UpdateUserInfoDto updateUserInfoDto) {
        userService.updateUserInfo(updateUserInfoDto);
    }

    @PatchMapping("/point")
    public void addPoint(@RequestBody ChangePointDto changePointDto) {
        userService.addPoint(changePointDto);
    }
}
