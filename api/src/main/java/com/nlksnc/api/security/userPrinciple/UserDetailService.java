package com.nlksnc.api.security.userPrinciple;

import com.nlksnc.api.exception.wrapper.EmailException;
import com.nlksnc.api.model.User;
import com.nlksnc.api.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new EmailException("User with e-mail " + username + "is not found"));

        return UserPrinciple.build(user);
    }
}
