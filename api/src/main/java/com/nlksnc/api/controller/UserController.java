package com.nlksnc.api.controller;

import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/email")
    public UserDto getUserByEmail(@RequestParam String email) {
        return userService.findByEmail(email);
    }

}
