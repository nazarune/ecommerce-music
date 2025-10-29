package com.nlksnc.api.security.userPrinciple;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nlksnc.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@With
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrinciple implements UserDetails {
    private Long id;
    private String name;
    private String surname;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserPrinciple build(User user) {
        List<GrantedAuthority> authorityList = user.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.name().name()))
                .collect(Collectors.toList());

        return UserPrinciple.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .password(user.getPassword())
                .authorities(authorityList)
                .build();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

}
