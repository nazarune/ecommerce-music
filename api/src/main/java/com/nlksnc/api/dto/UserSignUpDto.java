package com.nlksnc.api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserSignUpDto {
    private String email;
    private String name;
    private String surname;
    private String password;
}
