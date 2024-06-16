package com.example.coconut.domain.discussion_Type.service;


import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.category.entity.Category;


import com.example.coconut.domain.category.repository.CategoryRepository;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.repository.UserRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FreedcsService {
    private final FreedcsRepository freedcsRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Value("${custom.fileDirPath}")
    private String fileDirPath;

    private Specification<Freedcs> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Freedcs> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Freedcs, User> u1 = q.join("author", JoinType.LEFT);
                Join<Freedcs, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Freedcs, User> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }

//    public List<Category> getAllCategories() {
//        return categoryRepository.findAll();
//    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
    }

    public Page<Freedcs> getList(int page, String kw) {
        Pageable pageable = PageRequest.of(page, 12, Sort.by(Sort.Direction.DESC, "createDate"));
        if (kw == null || kw.isBlank()) {
            return freedcsRepository.findAll(pageable);
        }
//        return freedcsRepository.findAll(search(kw), pageable);


        return freedcsRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("title"), "%" + kw + "%"), pageable);

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createDate"));
        if (kw == null || kw.isBlank()) {
            return freedcsRepository.findAll(pageable);
        }
        return freedcsRepository.findAll(search(kw), pageable);
    }

    public Page<Freedcs> getListByCategory(int page, String kw, Long categoryId) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createDate"));
        if (kw == null || kw.isBlank()) {
            return freedcsRepository.findAllByCategory_Id(categoryId, pageable);
        }
//        return freedcsRepository.findAllByCategory_IdAndSearch(categoryId, kw, pageable);
        return freedcsRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("category").get("id"), categoryId),
                        criteriaBuilder.like(root.get("title"), "%" + kw + "%")
                ), pageable);
        return freedcsRepository.findAllByCategory_IdAndSearch(categoryId, kw, pageable);
    }

    public List<Freedcs> getPostsByCategory(Long categoryId) {
        return freedcsRepository.findAllByCategoryId(categoryId);
    }

    public List<Freedcs> getList() {
        return freedcsRepository.findAll();
    }



    public Freedcs getFreedcs(Long id) {
        Optional<Freedcs> freedcs = this.freedcsRepository.findById(id);
        if (freedcs.isPresent()) {
            return freedcs.get();
        } else {
            throw new DataNotFoundException("question not found");
        }
    }

    @Transactional
    public void incrementViews(Long id) {
        Freedcs freedcs = freedcsRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Freedcs not found"));
        freedcs.setView(freedcs.getView() + 1);
        freedcsRepository.save(freedcs);
    }

    public void free_create(String title, String content, MultipartFile thumbnail, User user, Category category){

    public void free_create(String title, String content, MultipartFile thumbnail, User user) {
    public void free_create(String title, String content, MultipartFile thumbnail, User user){

        String thumbnailRelPath = "freedcs/" + UUID.randomUUID().toString() + ".jpg";
        File thumbnailFile = new File(fileDirPath + "/" + thumbnailRelPath);



        try {
            thumbnail.transferTo(thumbnailFile);
        } catch ( IOException e ) {
            throw new RuntimeException(e);
        }

        Freedcs f = Freedcs.builder()
                .title(title)
                .content(content)
                .thumbnailImg(thumbnailRelPath)
                .author(user)
                .category(category)
                .build();
        freedcsRepository.save(f);

//        Freedcs f = new Freedcs();
//        f.setTitle(title);
//        f.setContent(content);
//        f.setThumbnailImg(thumbnail);
//        f.setAuthor(user);
//        this.freedcsRepository.save(f);
////        return f;
    public void free_create(String title, String content, User user){
        Freedcs f = new Freedcs();
        f.setTitle(title);
        f.setContent(content);
        f.setAuthor(user);
        this.freedcsRepository.save(f);
//        return f;
    }


    public void modify(Freedcs freedcs, String title, String content, Category category) {
        freedcs.setTitle(title);
        freedcs.setContent(content);
        freedcs.setCategory(category);
        this.freedcsRepository.save(freedcs);
    }

    public void delete(Freedcs freedcs){
        this.freedcsRepository.delete(freedcs);
    }


    public void vote(Freedcs freedcs, User user) {
        freedcs.getVoter().add(user);
        this.freedcsRepository.save(freedcs);
    }

    public void create(String title, String content) {
        Freedcs f = new Freedcs();
        f.setTitle(title);
        f.setContent(content);
//        f.setThumbnailImg(thumbnail);

        this.freedcsRepository.save(f);

    }

    //메인 페이지에 최신순으로 게시글 나열
    public List<Freedcs> findAllDiscussionsOrderByCreateDateDesc() {
        return freedcsRepository.findAllByOrderByCreateDateDesc();
    }

    //검색 페이지에 내가쓴 게시글 나열

    public List<Freedcs> getListByUserId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return freedcsRepository.findAllByAuthor(user);
        } else {
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }

    public List<Freedcs> findAllDiscussionsOrderByViewDesc() {
        return freedcsRepository.findAllByOrderByViewDesc();
    }


//    public void create(String title, String content, String thumbnail) {
//        Freedcs f = new Freedcs();
//        f.setTitle(title);
//        f.setContent(content);
//        f.setThumbnailImg(thumbnail);
//
//        this.freedcsRepository.save(f);
//
//    }


}





