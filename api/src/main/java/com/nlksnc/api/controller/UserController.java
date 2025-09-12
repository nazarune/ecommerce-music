package com.nlksnc.api.controller;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.mapper.UserMapper;
import com.nlksnc.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        var user = userService.findById(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/email")
    public UserDto getUserByEmail(@RequestParam String email) {
        var user = userService.findByEmail(email);
        return userMapper.toDto(user);
    }

}
