package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.UserLogInDto;
import com.nlksnc.api.dto.UserSignUpDto;
import com.nlksnc.api.model.User;

public interface UserAuthService {
    User register(UserSignUpDto userSignUpDto);

    User login(UserLogInDto userLogInDto);
}
