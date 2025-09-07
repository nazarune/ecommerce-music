package com.nlksnc.api.controller;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.dto.UserLogInDto;
import com.nlksnc.api.dto.UserSignUpDto;
import com.nlksnc.api.mapper.UserMapper;
import com.nlksnc.api.service.interfaces.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserAuthController {
    private final UserAuthService userAuthService;
    private final UserMapper userMapper;

    @PostMapping("/register")
    public UserDto register(@RequestBody UserSignUpDto userSignUpDto) {
        var user = userAuthService.register(userSignUpDto);
        return userMapper.toDto(user);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserLogInDto userLogInDto) {
        var user = userAuthService.login(userLogInDto);
        return userMapper.toDto(user);
    }
}
