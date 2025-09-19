package com.nlksnc.api.controller;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.dto.UserLogInDto;
import com.nlksnc.api.dto.UserSignUpDto;
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

    @PostMapping("/register")
    public UserDto register(@RequestBody UserSignUpDto userSignUpDto) {
        return userAuthService.register(userSignUpDto);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody UserLogInDto userLogInDto) {
        return userAuthService.login(userLogInDto);
    }
}
