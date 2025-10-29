package com.nlksnc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformationMessage {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> roles;
}
