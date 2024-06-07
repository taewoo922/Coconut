package com.example.coconut.domain.user.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReportRepository reportRepository;

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

    public User getUser(String username) {

        Optional<User> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("user not found");
        }
    }


    public List<Report> getListByUserId(Long userId) {
        return this.reportRepository.findAllByAuthorId(userId); // 사용자의 ID로 해당 사용자가 작성한 게시물들을 가져오는 메서드
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new DataNotFoundException("User not found with username: " + username));
    }





}
