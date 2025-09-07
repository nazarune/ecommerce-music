package com.nlksnc.api.dto;

import com.nlksnc.api.model.Role;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private Role role;
}
