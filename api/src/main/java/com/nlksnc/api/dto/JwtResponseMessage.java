package com.nlksnc.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseMessage {
    private String accessToken;
    private String refreshToken;
    private InformationMessage user;
}
