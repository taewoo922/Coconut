package com.example.coconut.domain.discussion_Type.controller;

import com.example.coconut.domain.answer.AnswerForm;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.DebateForm;
import com.example.coconut.domain.discussion_Type.FreedcsForm;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class DebateController {

    private final DebateService debateService;
    private final UserService userService;
    private final CategoryService categoryService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/debate_list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw,
                       @RequestParam(value = "category", required = false) Long categoryId) {
        Page<Debate> paging_category;
        List<Debate> debateList;

       paging_category = this.debateService.getListByCategory(page, kw, categoryId);
       debateList = this.debateService.getPostsByCategory(categoryId);

        Page<Debate> paging = this.debateService.getList(page, kw);
        model.addAttribute("paging_category", paging_category);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("debateList", debateList);

        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "discussion/debate_list";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/d_detail/{id}")
    public String d_detail(Model model, @PathVariable("id") Long id, AnswerForm answerForm) {
        Debate debate = this.debateService.getDebate(id);
        List<Answer> answers = debate.getAnswerList();

        long supportCount = answers.stream().filter(answer -> answer.isSupport()).count();
        long oppositionCount = answers.stream().filter(answer -> !answer.isSupport()).count();

        model.addAttribute("debate", debate);
        model.addAttribute("supportCount", supportCount);
        model.addAttribute("oppositionCount", oppositionCount);


        return "discussion/d_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/d_create")
    public String d_create(DebateForm debateForm, Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "discussion/d_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/d_create")
    public String d_create(@Valid DebateForm debateForm, BindingResult bindingResult, Principal principal,
                           @RequestParam("thumbnail") MultipartFile thumbnail) {
        if (bindingResult.hasErrors()) {
            return "discussion/d_create_form";
        }

        User user = this.userService.getUser(principal.getName());

        Category category = this.categoryService.getCategoryById(debateForm.getCategory());
        this.debateService.d_create(debateForm.getTitle(), debateForm.getContent(), debateForm.getThumbnail(), user, category);


        return "redirect:/discussion/debate_list";

    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/d_modify/{id}")
    public String dModify(DebateForm debateForm, @PathVariable("id") Long id, Principal principal, Model model){
        Debate debate = this.debateService.getDebate(id);
        if(!debate.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        debateForm.setTitle(debate.getTitle());
        debateForm.setContent(debate.getContent());
        debateForm.setCategory(debate.getCategory().getId());



        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "discussion/d_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/d_modify/{id}")
    public String dModify(@Valid DebateForm debateForm, BindingResult bindingResult,
                               Principal principal, @PathVariable("id") Long id,
                          @RequestParam("thumbnail") MultipartFile thumbnail) {
        if (bindingResult.hasErrors()) {
            return "discussion/d_create_form";
        }
        Debate debate = this.debateService.getDebate(id);
        if(!debate.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        Category category = this.categoryService.getCategoryById(debateForm.getCategory());


        this.debateService.modify(debate, debateForm.getTitle(), debateForm.getContent(), category, thumbnail);
        return "redirect:/discussion/d_detail/%s".formatted(id);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/d_delete/{id}")
    public String debateDelete(Principal principal, @PathVariable("id") Long id){
        Debate debate = this.debateService.getDebate(id);
        if(!debate.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
        }
        this.debateService.delete(debate);
        return "redirect:/discussion/debate_list";
    }



    @PreAuthorize("isAuthenticated()")
    @GetMapping("/d_vote/{id}")
    public String d_Vote(Principal principal, @PathVariable("id") Long id) {
        Debate debate = this.debateService.getDebate(id);
        User user = this.userService.getUser(principal.getName());

        this.debateService.vote(debate, user);

        return String.format("redirect:/discussion/d_detail/%s".formatted(id));
    }

}
