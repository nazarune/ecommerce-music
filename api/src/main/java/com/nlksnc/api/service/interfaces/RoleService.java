package com.nlksnc.api.service.interfaces;

import com.nlksnc.api.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    Optional<Role> findRole(String name);

    boolean assignRole(Long userId, String name);

    boolean removeRole(Long userId, String name);

    List<String> getUserRoles(Long userId);
}
