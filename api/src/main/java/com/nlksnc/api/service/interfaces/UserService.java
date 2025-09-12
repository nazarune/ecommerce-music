package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.model.User;

public interface UserService {
    User findById(Long id);
    User findByEmail(String email);
}
