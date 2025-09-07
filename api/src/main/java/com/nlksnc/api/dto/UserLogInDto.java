package com.nlksnc.api.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserLogInDto {
    private String email;
    private String password;
}
