package com.example.coconut.domain.user.controller;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.scrap.entity.Scrap;
import com.example.coconut.domain.scrap.service.ScrapService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.entity.UserRole;
import com.example.coconut.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ReportService reportService;
    private final FreedcsService freedcsService;
    private final DebateService debateService;
    private final ScrapService scrapService;

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
        userService.signup(signForm.getUsername(), signForm.getPassword(), signForm.getNickname(), signForm.getEmail(), signForm.getPhone(), UserRole.ADMIN);
        return "redirect:/user/login";

    }

    @GetMapping("/search")
    public String searchPage(Model model, Principal principal){
        String username = principal.getName(); // 현재 로그인된 사용자의 이름
        User user = userService.getUserByUsername(username); // 사용자 이름을 이용하여 사용자 정보 가져오기
        List<Report> reportList = reportService.getListByUserId(user.getId()); // 사용자 ID를 이용하여 해당 사용자의 게시물 가져오기
        model.addAttribute("reportList", reportList);

        // 사용자가 작성한 자유토론 테이블의 게시물 가져오기
        List<Freedcs> freedcsList = freedcsService.getListByUserId(user.getId());
        model.addAttribute("freedcsList", freedcsList);

        // 사용자가 작성한 찬/반토론 테이블의 게시물 가져오기
        List<Debate> debateList = debateService.getListByUserId(user.getId());
        model.addAttribute("debateList", debateList);

        List<Scrap> scrapList = scrapService.getListByUserId(user.getId());
        model.addAttribute("scrapList", scrapList);

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
    @PostMapping("/profile/delete")
    public String deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        userService.deleteCurrentUser();
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/user/login?accountDeleted";
    }
    @GetMapping("/find-username")
    public String showFindUsernamePage(Model model) {
        model.addAttribute("username", null); // 초기화: 아이디 결과 없음
        model.addAttribute("error", null); // 초기화: 에러 메시지 없음
        return "user/find-username"; // templates/user/find-username.html 경로에 파일이 있어야 함
    }

    // 아이디 찾기 기능 처리
    @PostMapping("/find-username")
    public String processFindUsername(@RequestParam("email") String email, Model model) {
        try {
            String username = userService.findUsernameByEmail(email); // 이메일로 아이디 찾기
            model.addAttribute("username", username); // 결과를 모델에 추가
            model.addAttribute("error", null); // 에러 메시지 초기화
        } catch (DataNotFoundException e) {
            model.addAttribute("username", null); // 아이디 결과 초기화
            model.addAttribute("error", "해당 이메일로 등록된 사용자가 없습니다."); // 에러 메시지 설정
        }
        return "user/find-username"; // 결과 표시 페이지로 이동
    }
    @GetMapping("/reset-password")
    public String showResetPasswordPage(Model model) {
        model.addAttribute("successMessage", null);
        model.addAttribute("errorMessage", null);
        return "user/reset-password";
    }

    @PostMapping("/check-user")
    @ResponseBody
    public ResponseEntity<Map<String, Boolean>> checkUser(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String email = request.get("email");

        Map<String, Boolean> response = new HashMap<>();
        try {
            userService.findUserByUsernameAndEmail(username, email);
            response.put("exists", true);
        } catch (DataNotFoundException e) {
            response.put("exists", false);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/reset-password")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> resetPassword(@RequestParam("username") String username,
                                                             @RequestParam("email") String email,
                                                             @RequestParam("newPassword") String newPassword) {
        Map<String, Object> response = new HashMap<>();
        try {
            userService.resetPassword(username, email, newPassword);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("errorMessage", "비밀번호 변경 중 오류가 발생했습니다: " + e.getMessage());
        }
        return ResponseEntity.ok(response);
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
