package com.nlksnc.api.controller;

import com.nlksnc.api.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/{id}/assign")
    public ResponseEntity<HttpStatus> assignRole(@PathVariable Long id, @RequestParam String role) {
        boolean result = roleService.assignRole(id, role);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @PostMapping("/{id}/remove")
    public ResponseEntity<HttpStatus> removeRole(@PathVariable Long id, @RequestParam String role) {
        boolean result = roleService.removeRole(id, role);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }

    @GetMapping("/{id}/user")
    public ResponseEntity<List<String>> getUserRoles(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getUserRoles(id));
    }
}