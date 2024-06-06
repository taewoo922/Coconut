package com.example.coconut.domain.user.controller;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ReportService reportService;

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
        return "user/search";
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
}
