package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.dto.UserDto;

public interface UserService {
    UserDto findById(Long id);

    UserDto findByEmail(String email);
}
