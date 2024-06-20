package com.example.coconut.domain.user.controller;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ReportService reportService;
    private final FreedcsService freedcsService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignForm signForm) {
        userService.signup(signForm.getUsername(), signForm.getPassword(), signForm.getNickname(), signForm.getEmail(), signForm.getPhone());
        return "redirect:/user/login";

    }

    @GetMapping("/search")
    public String searchPage(Model model, Principal principal){
        String username = principal.getName(); // 현재 로그인된 사용자의 이름
        User user = userService.getUserByUsername(username); // 사용자 이름을 이용하여 사용자 정보 가져오기
        List<Report> reportList = reportService.getListByUserId(user.getId()); // 사용자 ID를 이용하여 해당 사용자의 게시물 가져오기
        model.addAttribute("reportList", reportList);

        // 사용자가 작성한 freedcs 테이블의 게시물 가져오기
        List<Freedcs> freedcsList = freedcsService.getListByUserId(user.getId());
        model.addAttribute("freedcsList", freedcsList);

        return "user/search";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profilePage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/profile";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile/edit")
    public String editProfilePage(Model model) {
        User user = userService.getCurrentUser();
        model.addAttribute("user", user);
        return "user/edit-profile";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/profile/edit")
    public String editProfile(@Valid EditProfileForm form, @RequestParam("profileImageFile") MultipartFile profileImageFile) throws IOException {
        userService.updateProfile(form, profileImageFile);
        return "redirect:/user/profile";
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/posts")
    public String myPostsPage(Model model) {
        // 사용자의 게시물
        return "user/posts";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/scrap")
    public String myScrapPage(Model model) {
        // 사용자의 스크랩 목록
        return "user/scrap";
    }


    @Getter
    @Setter
    @ToString
    public static class SignForm {
        @NotBlank
        @Length(min = 3)
        private String username;

        @NotBlank
        @Length(min = 4)
        private String password;

        @NotBlank
        @Length(min = 4)
        private String password_confirm;

        @NotBlank
        @Length(min = 2)
        private String nickname;

        @NotBlank
        @Length(min = 4)
        private String email;

        @NotBlank
        @Length(min = 4)
        private String phone;
    }
    @Getter
    @Setter
    @ToString
    public static class EditProfileForm {

        @NotBlank
        private String nickname;

        // 비밀번호가 비어도 수정할 수 있도록 변경
        private String password;

        @NotBlank
        private String phone;

        private MultipartFile profileImageFile;
    }
}
