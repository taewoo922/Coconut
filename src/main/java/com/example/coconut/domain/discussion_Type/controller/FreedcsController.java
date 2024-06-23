package com.example.coconut.domain.discussion_Type.controller;

import com.example.coconut.domain.answer.AnswerForm;
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.discussion_Type.FreedcsForm;
import com.example.coconut.domain.category.repository.CategoryRepository;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.form.ReportForm;
import com.example.coconut.domain.scrap.service.ScrapService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/discussion")
public class FreedcsController {

    private final FreedcsService freedcsService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ScrapService scrapService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/freedcs_list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page,
                       @RequestParam(value = "kw", defaultValue = "") String kw,
                       @RequestParam(value = "category", required = false) Long categoryId) {

        Page<Freedcs> paging_category;
        List<Freedcs> freedcsList;

        paging_category = this.freedcsService.getListByCategory(page, kw, categoryId);
        freedcsList = this.freedcsService.getPostsByCategory(categoryId);

        Page<Freedcs> paging = this.freedcsService.getList(page, kw);
        model.addAttribute("paging_category", paging_category);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        model.addAttribute("freedcsList", freedcsList);


        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        return "discussion/freedcs_list";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/free_detail/{id}")
    public String free_detail(Model model, @PathVariable("id") Long id, @RequestParam(value = "view", defaultValue = "0") int view, AnswerForm answerForm) {
        this.freedcsService.incrementViews(id);
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        model.addAttribute("freedcs", freedcs);
        return "discussion/freedcs_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/free_create")
    public String free_create(FreedcsForm freedcsForm, Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "discussion/free_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/free_create")
    public String free_create(@Valid FreedcsForm freedcsForm, BindingResult bindingResult, Principal principal,
                              @RequestParam("thumbnail") MultipartFile thumbnail) {
        if (bindingResult.hasErrors()) {
            return "discussion/free_create_form";
        }

        User user = this.userService.getUser(principal.getName());

        Category category = this.categoryService.getCategoryById(freedcsForm.getCategory());
        this.freedcsService.free_create(freedcsForm.getTitle(), freedcsForm.getContent(), freedcsForm.getThumbnail(), user, category);

        return "redirect:/discussion/freedcs_list";

    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/freedcs/scrap/{id}")
    public String getFreedcsById(@PathVariable("id") Long id, Model model, AnswerForm answerForm) {
        Freedcs freedcs = freedcsService.getFreedcs(id);

        model.addAttribute("answerForm", answerForm);
        model.addAttribute("freedcs", freedcs);
        model.addAttribute("scrap", scrapService.getScrapsByFreedcsId(id));
        return "discussion/freedcs_detail";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/freedcs/scrap/{id}")
    public String addScrap(@PathVariable("id") Long id,  Principal principal) {
        User user = this.userService.getUser(principal.getName());

        scrapService.addScrap(id,user);

        return "redirect:/discussion/free_detail/{id}";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String freedcsModify(FreedcsForm freedcsForm, @PathVariable("id") Long id, Principal principal, Model model){
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        if(!freedcs.getAuthor().getUsername().equals(principal.getName())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }

        freedcsForm.setTitle(freedcs.getTitle());
        freedcsForm.setContent(freedcs.getContent());
        freedcsForm.setCategory(freedcs.getCategory().getId());


        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);


        return "discussion/free_create_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String freedcsModify(@Valid FreedcsForm freedcsForm, BindingResult bindingResult,
                                Principal principal, @PathVariable("id") Long id,
                                @RequestParam("thumbnail") MultipartFile thumbnail
                                ) {
        if (bindingResult.hasErrors()) {
            return "discussion/free_create_form";
        }
        Freedcs freedcs = this.freedcsService.getFreedcs(id);
        if(!freedcs.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }


        Category category = this.categoryService.getCategoryById(freedcsForm.getCategory());

        this.freedcsService.modify(freedcs, freedcsForm.getTitle(), freedcsForm.getContent(), category, thumbnail);
        return "redirect:/discussion/free_detail/%s".formatted(id);

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

        return String.format("redirect:/discussion/free_detail/%s".formatted(id));
    }
}
