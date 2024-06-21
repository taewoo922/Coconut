package com.example.coconut.domain.user.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.user.controller.UserController;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.entity.UserRole;
import com.example.coconut.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ReportRepository reportRepository;

    public User signup(String username, String password, String nickname, String email, String phone, UserRole admin) {
        User user = User
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .email(email)
                .phone(phone)
                .role(username.equals("admin") ? UserRole.ADMIN : UserRole.USER) // admin 사용자에게 ADMIN 역할 부여
                .build();
        return userRepository.save(user);
    }

    @Transactional
    public User whenSocialLogin(String providerTypeCode, String username, String nickname) {
        Optional<User> opUser = userRepository.findByUsername(username);

        if (opUser.isPresent()) return opUser.get();

        return signup(username, "", nickname, "", "", UserRole.ADMIN); // 최초 로그인 시 딱 한 번 실행
    }

    public User getUser(String username) {

        Optional<User> siteUser = this.userRepository.findByUsername(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new DataNotFoundException("user not found");
        }

    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username).orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public void updateProfile(UserController.EditProfileForm form, MultipartFile profileImageFile) throws IOException {
        User user = getCurrentUser();
        user.setNickname(form.getNickname());
        user.setPhone(form.getPhone());

        if (profileImageFile != null && !profileImageFile.isEmpty()) {
            String profileImagePath = saveProfileImage(profileImageFile);
            user.setProfileImagePath(profileImagePath);
        }

        if (form.getPassword() != null && !form.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(form.getPassword()));
        }

        userRepository.save(user);
    }

    private String saveProfileImage(MultipartFile profileImageFile) throws IOException {
        String uploadDir = "C:/work/thumbnail/";
        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdirs();
        }

        String originalFilename = profileImageFile.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFilename = UUID.randomUUID().toString() + extension;
        File destinationFile = new File(uploadDir + newFilename);
        profileImageFile.transferTo(destinationFile);

        return newFilename;
    }

    public List<Report> getListByUserId(Long userId) {
        return this.reportRepository.findAllByAuthorId(userId); // 사용자의 ID로 해당 사용자가 작성한 게시물들을 가져오는 메서드
    }

    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElseThrow(() -> new DataNotFoundException("User not found with username: " + username));
    }

    public List<User> getList() {
        return userRepository.findAll();
    }

    public Page<User> getList(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createDate"));
        if (kw == null || kw.isBlank()) {
            return userRepository.findAll(pageable);
        }

        return userRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("username"), "%" + kw + "%"), pageable);

    }

    public void deleteUser(Long userId) {

        Optional<User> siteUser = userRepository.findById(userId);

        if (siteUser.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new RuntimeException("회원이 존재하지 않습니다.");
        }

    }
}
