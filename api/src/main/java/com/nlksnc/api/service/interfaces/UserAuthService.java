package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.dto.UserLogInDto;
import com.nlksnc.api.dto.UserSignUpDto;

public interface UserAuthService {
    UserDto register(UserSignUpDto userSignUpDto);

    UserDto login(UserLogInDto userLogInDto);
}
