package com.example.coconut.domain.user.service;

import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User signup(String username, String password, String nickname, String email, String phone) {
        User user = User
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .phone(phone)
                .build();
        return userRepository.save(user);
    }

}