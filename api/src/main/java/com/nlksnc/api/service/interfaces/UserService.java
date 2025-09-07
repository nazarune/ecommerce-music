package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findById(Long id);

    User getByIdOrThrow(Long id);

    User getByEmailOrThrow(String email);
}
