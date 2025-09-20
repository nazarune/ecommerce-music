package com.nlksnc.api.service.impl;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.dto.UserLogInDto;
import com.nlksnc.api.dto.UserSignUpDto;
import com.nlksnc.api.exception.wrapper.EmailException;
import com.nlksnc.api.exception.wrapper.PasswordException;
import com.nlksnc.api.mapper.UserMapper;
import com.nlksnc.api.mapper.UserSignUpMapper;
import com.nlksnc.api.model.Role;
import com.nlksnc.api.model.User;
import com.nlksnc.api.repository.UserRepository;
import com.nlksnc.api.service.interfaces.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSignUpMapper userSignUpMapper;

    @Override
    public UserDto register(UserSignUpDto userSignUpDto) {
        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new EmailException("User with email: " + userSignUpDto.getEmail() + " already exists");
        }
        User user = userSignUpMapper.toEntity(userSignUpDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.CUSTOMER);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto login(UserLogInDto userLogInDto) {
        if (userRepository.findByEmail(userLogInDto.getEmail()).isEmpty()) {
            throw new EmailException("User with email: " + userLogInDto.getEmail() + " not found");
        }
        User user = userRepository.findByEmail(userLogInDto.getEmail()).get();
        if (!passwordEncoder.matches(userLogInDto.getPassword(), user.getPassword())) {
            throw new PasswordException("Wrong password");
        }
        return userMapper.toDto(user);
    }
}
