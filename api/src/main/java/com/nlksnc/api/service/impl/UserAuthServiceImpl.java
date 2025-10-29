package com.nlksnc.api.service.impl;

import com.nlksnc.api.dto.InformationMessage;
import com.nlksnc.api.dto.JwtResponseMessage;
import com.nlksnc.api.dto.UserDto;
import com.nlksnc.api.dto.UserLogInDto;
import com.nlksnc.api.dto.UserSignUpDto;
import com.nlksnc.api.exception.wrapper.EmailException;
import com.nlksnc.api.exception.wrapper.PasswordException;
import com.nlksnc.api.exception.wrapper.UserException;
import com.nlksnc.api.mapper.UserMapper;
import com.nlksnc.api.mapper.UserSignUpMapper;
import com.nlksnc.api.model.User;
import com.nlksnc.api.repository.UserRepository;
import com.nlksnc.api.security.jwt.JwtProvider;
import com.nlksnc.api.security.userPrinciple.UserDetailService;
import com.nlksnc.api.security.userPrinciple.UserPrinciple;
import com.nlksnc.api.service.interfaces.RoleService;
import com.nlksnc.api.service.interfaces.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {
    private final UserRepository userRepository;
    private final UserDetailService userDetailService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserSignUpMapper userSignUpMapper;
    private final RoleService roleService;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<UserDto> register(UserSignUpDto userSignUpDto) {
        if (userRepository.findByEmail(userSignUpDto.getEmail()).isPresent()) {
            throw new EmailException("User with email: " + userSignUpDto.getEmail() + " already exists");
        }
        User user = userSignUpMapper.toEntity(userSignUpDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roleService.findRole("CUSTOMER").stream().collect(Collectors.toSet()));
        return ResponseEntity.ok(userMapper.toDto(userRepository.save(user)));
    }

    @Override
    public ResponseEntity<JwtResponseMessage> login(UserLogInDto userLogInDto) {
        UserDetails userDetails = userDetailService.loadUserByUsername(userLogInDto.getEmail());
        if (userDetails == null) {
            throw new UserException("User not found");
        }
        if (!passwordEncoder.matches(userLogInDto.getPassword(), userDetails.getPassword())) {
            throw new PasswordException("Wrong password");
        }
        Authentication auth = new UsernamePasswordAuthenticationToken(
                userDetails,
                userLogInDto.getPassword(),
                userDetails.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        UserPrinciple userPrinciple = (UserPrinciple) userDetails;

        String accessToken = jwtProvider.createToken(auth);
        String refreshToken = jwtProvider.createRefreshToken(auth);

        return ResponseEntity.ok(
                JwtResponseMessage.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .user(InformationMessage.builder()
                                .id(userPrinciple.id())
                                .name(userPrinciple.name())
                                .surname(userPrinciple.surname())
                                .email(userPrinciple.email())
                                .roles(userPrinciple.authorities())
                                .build())
                        .build()
        );
    }

    @Override
    public ResponseEntity<Void> logout() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.getContext().setAuthentication(null);
        String currentToken = getCurrentToken(auth);
        if (auth != null && auth.isAuthenticated()) {
            jwtProvider.reduceTokenExpiration(currentToken);
        }
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok().build();
    }

    private String getCurrentToken(Authentication auth) {
        if (auth != null && auth.isAuthenticated()) {
            Object credentials = auth.getCredentials();
            if (credentials instanceof String) {
                return (String) credentials;
            }
        }
        return null;
    }
}
