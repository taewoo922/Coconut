package com.example.coconut.domain.admin.controller;

import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
@Getter
@Setter
public class AdminController {

    private final UserService userService;
    private final ReportService reportService;
    private final FreedcsService freedcsService;
    private final DebateService debateService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/userlist")
    public String userlist(Model model,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           @RequestParam(value = "kw", defaultValue = "") String kw
    ) {

        Page<User> paging = this.userService.getList(page, kw);;

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "manager/admin";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/user/delete/{id}")
    public String deleteUser (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("message", "회원이 성공적으로 탈퇴되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "회원 탈퇴 중 오류가 발생했습니다.");
        }
        return "redirect:/userlist";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/questionlist")
    public String questionlist(Model model,
                               @RequestParam(value = "page", defaultValue = "0") int page,
                               @RequestParam(value = "kw", defaultValue = "") String kw
    ) {

        Page<Report> paging = reportService.getList(page, kw);


        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);


        return "manager/questionlist";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/question/delete/{id}")
    public String deletequestion (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        try {
            reportService.deletereport(id);
            redirectAttributes.addFlashAttribute("message", "질문이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "질문 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/questionlist";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/freedcslist")
    public String freedcslist(Model model,
                              @RequestParam(value = "page", defaultValue = "0") int page,
                              @RequestParam(value = "kw", defaultValue = "") String kw
    ) {
        Page<Freedcs> paging = freedcsService.getList(page, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "manager/freedcslist";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/freedcs/delete/{id}")
    public String deletefreedcs (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        try {
            freedcsService.deletefreedcs(id);
            redirectAttributes.addFlashAttribute("message", "질문이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "질문 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/questionlist";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/debatelist")
    public String debatelist(Model model,
                             @RequestParam(value = "page", defaultValue = "0") int page,
                             @RequestParam(value = "kw", defaultValue = "") String kw
    ) {
        Page<Debate> paging = debateService.getList(page, kw);

        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);

        return "manager/debatelist";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/admin/debate/delete/{id}")
    public String deletedebate (@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {

        try {
            debateService.deletereport(id);
            redirectAttributes.addFlashAttribute("message", "질문이 성공적으로 삭제되었습니다.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "질문 삭제 중 오류가 발생했습니다.");
        }
        return "redirect:/questionlist";
    }

}
