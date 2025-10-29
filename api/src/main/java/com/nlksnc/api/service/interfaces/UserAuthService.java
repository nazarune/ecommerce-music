package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.JwtResponseMessage;
import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.dto.UserLogInDto;
import com.nlksnc.api.dto.UserSignUpDto;
import org.springframework.http.ResponseEntity;

public interface UserAuthService {
    ResponseEntity<UserDto> register(UserSignUpDto userSignUpDto);

    ResponseEntity<JwtResponseMessage> login(UserLogInDto userLogInDto);

    ResponseEntity<Void> logout();
}
