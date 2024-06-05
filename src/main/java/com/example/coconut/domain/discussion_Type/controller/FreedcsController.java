package com.example.coconut.domain.discussion_Type.controller;

import com.example.coconut.domain.answer.AnswerForm;
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.form.ReportForm;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class FreedcsController {

    private final FreedcsService freedcsService;
    private final UserService userService;

//    @Autowired
//    private CategoryService categoryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/freedcs_list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "kw", defaultValue = "") String kw) {
        Page<Freedcs> paging = this.freedcsService.getList(page, kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        List<Freedcs> freedcsList = this.freedcsService.getList();
        model.addAttribute("freedcsList",freedcsList);
        return "discussion/freedcs_list";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/free_detail/{id}")
    public String free_detail(Model model, @PathVariable("id") Long id, AnswerForm answerForm) {
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        model.addAttribute("freedcs", freedcs);
        return "discussion/freedcs_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/free_create")
    public String free_create(FreedcsForm freedcsForm) {
        return "discussion/free_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/free_create")
    public String free_Create(@Valid FreedcsForm freedcsForm, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "discussion/free_create_form";
        }
        User user = this.userService.getUser(principal.getName());
        this.freedcsService.free_create(freedcsForm.getTitle(), freedcsForm.getContent(), user);
        return "redirect:/discussion/freedcs_list";
//    public String free_create(@RequestParam("title") String title, @RequestParam("content") String content,
//                              @RequestParam("thumbnail") MultipartFile thumbnail) {
//
//        freedcsService.free_create(title,content,thumbnail);
//
//
//        return "redirect:/discussion/freedcs_list";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String freedcsModify(FreedcsForm freedcsForm, @PathVariable("id") Long id, Principal principal){
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        if(!freedcs.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        freedcsForm.setTitle(freedcs.getTitle());
        freedcsForm.setContent(freedcs.getContent());
        return "discussion/free_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String freedcsModify(@Valid FreedcsForm freedcsForm, BindingResult bindingResult,
                               Principal principal, @PathVariable("id") Long id) {
        if (bindingResult.hasErrors()) {
            return "discussion/free_create_form";
        }
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        if(!freedcs.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.freedcsService.modify(freedcs, freedcsForm.getTitle(), freedcsForm.getContent());
        return "redirect:/discussion/freedcs_detail/%s".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String freedcsDelete(Principal principal, @PathVariable("id") Long id){
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        if(!freedcs.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.freedcsService.delete(freedcs);
        return "redirect:/discussion/freedcs_list";
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/vote/{id}")
    public String free_Vote(Principal principal, @PathVariable("id") Long id) {
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        User user = this.userService.getUser(principal.getName());

        this.freedcsService.vote(freedcs, user);

        return String.format("redirect:/discussion/freedcs_detail/%s".formatted(id));
    }



    @ModelAttribute("categories")
    public List<Category> categories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Politics", "정치"));
        categories.add(new Category("Economy", "경제"));
        categories.add(new Category("Sports", "스포츠"));
        return categories;
    }



}
