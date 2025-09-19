package com.nlksnc.api.service.impl;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.mapper.UserMapper;
import com.nlksnc.api.repository.UserRepository;
import com.nlksnc.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDto findById(Long id) {
        return userMapper.toDto(userRepository.findById(id).orElseThrow());
    }

    @Override
    public UserDto findByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow());
    }

}
