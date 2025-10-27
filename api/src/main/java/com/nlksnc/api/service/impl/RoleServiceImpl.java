package com.nlksnc.api.service.impl;

import com.nlksnc.api.exception.wrapper.RoleException;
import com.nlksnc.api.exception.wrapper.UserException;
import com.nlksnc.api.model.ERole;
import com.nlksnc.api.model.Role;
import com.nlksnc.api.model.User;
import com.nlksnc.api.repository.RoleRepository;
import com.nlksnc.api.repository.UserRepository;
import com.nlksnc.api.service.interfaces.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public Optional<Role> findRole(String name) {
        return roleRepository.findByName(mapToERole(name));
    }

    @Override
    @Transactional
    public boolean assignRole(Long userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with id " + userId + " not found"));

        Role role = roleRepository.findByName(mapToERole(name))
                .orElseThrow(() -> new RoleException("Role with name " + name + " not found"));

        if (user.getRoles().contains(role)) {
            return false;
        }

        user.getRoles().add(role);
        userRepository.save(user);
        return true;
    }

    @Override
    @Transactional
    public boolean removeRole(Long userId, String name) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with id " + userId + " not found"));

        Role role = roleRepository.findByName(mapToERole(name))
                .orElseThrow(() -> new RoleException("Role with name " + name + " not found"));

        if (user.getRoles().contains(role)) {
            user.getRoles().remove(role);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<String> getUserRoles(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserException("User with id " + userId + " not found"));

        List<String> roles = new ArrayList<>();
        user.getRoles().forEach(role -> roles.add(role.name().toString()));
        return roles;
    }

    private ERole mapToERole(String roleName) {
        return switch (roleName.toUpperCase()) {
            case "ADMIN" -> ERole.ADMIN;
            case "CUSTOMER" -> ERole.CUSTOMER;
            case "MANAGER" -> ERole.MANAGER;
            default -> null;
        };
    }
}
