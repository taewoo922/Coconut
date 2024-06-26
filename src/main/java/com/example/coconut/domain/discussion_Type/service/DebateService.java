package com.example.coconut.domain.discussion_Type.service;


import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.repository.CategoryRepository;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.repository.DebateRepository;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebateService {
    private final DebateRepository debateRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Value("${custom.fileDirPath}")
    private String fileDirPath;



    private Specification<Debate> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Debate> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Debate, User> u1 = q.join("author", JoinType.LEFT);
                Join<Debate, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Debate, User> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }


    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new DataNotFoundException("Category not found"));
    }

    public Page<Debate> getList(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 12, Sort.by(sorts));
        Specification<Debate> spec = search(kw);
        Page<Debate> debates = this.debateRepository.findAll(spec, pageable);

        debates.forEach(debate -> {
            if (debate.getAuthor() != null) {
                debate.setAuthorNickname(debate.getAuthor().getNickname());
            } else {
                debate.setAuthorNickname("Unknown");
            }
        });

        return debates;
    }

    public Page<Debate> getListByCategory(int page, String kw, Long categoryId) {
        Pageable pageable = PageRequest.of(page, 12, Sort.by(Sort.Direction.DESC, "createDate"));
        if (kw == null || kw.isBlank()) {
            return debateRepository.findAllByCategory_Id(categoryId, pageable);
        }

        return debateRepository.findAll((root, query, criteriaBuilder) ->
                criteriaBuilder.and(
                        criteriaBuilder.equal(root.get("category").get("id"), categoryId),
                        criteriaBuilder.like(root.get("title"), "%" + kw + "%")
                ), pageable);
    }

    public List<Debate> getPostsByCategory(Long categoryId) {
        return debateRepository.findAllByCategoryId(categoryId);
    }

    public List<Debate> getList() {
        return debateRepository.findAll();
    }




    public Debate getDebate(Long id) {
        Optional<Debate> debate = this.debateRepository.findById(id);
        if (debate.isPresent()) {
            return debate.get();
        } else {
            throw new DataNotFoundException("게시물을 찾을 수 없습니다.");
        }
    }

    @Transactional
    public void incrementViews(Long id) {
        Debate debate = debateRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("게시물을 찾을 수 없습니다."));
        debate.setView(debate.getView() + 1);
        debateRepository.save(debate);
    }

    public void d_create(String title, String content, MultipartFile thumbnail, User user, Category category){

        String thumbnailRelPath;
        if (!thumbnail.isEmpty()) {
            thumbnailRelPath = UUID.randomUUID().toString() + ".jpg";
            File thumbnailFile = new File(fileDirPath + "/" + thumbnailRelPath);
            try {
                thumbnail.transferTo(thumbnailFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // 기본 이미지 경로 설정
            thumbnailRelPath = "freedcs/coconut.png";
        }

        Debate d = Debate.builder()
                .title(title)
                .content(content)
                .thumbnailImg(thumbnailRelPath)
                .author(user)
                .category(category)
                .build();
        debateRepository.save(d);
    }


    public void modify(Debate debate, String title, String content, Category category, MultipartFile thumbnail) {
        debate.setTitle(title);
        debate.setContent(content);
        debate.setCategory(category);

        if (!thumbnail.isEmpty()) {
            try {
                // 기존 썸네일 파일 삭제
                if (debate.getThumbnailImg() != null) {
                    java.nio.file.Path oldFilePath = Paths.get(fileDirPath, debate.getThumbnailImg());
                    Files.deleteIfExists(oldFilePath);
                }

                // 새로운 썸네일 파일 저장
                String originalFileName = thumbnail.getOriginalFilename();
                String fileName = System.currentTimeMillis() + "_" + originalFileName;
                Path filePath = Paths.get(fileDirPath, fileName);
                Files.copy(thumbnail.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                debate.setThumbnailImg(fileName);
            } catch (IOException e) {
                throw new RuntimeException("Failed to save thumbnail file", e);
            }
        }

        this.debateRepository.save(debate);
    }

    public void delete(Debate debate){
        this.debateRepository.delete(debate);
    }

    public void vote(Debate debate, User user) {
        debate.getVoter().add(user);
        this.debateRepository.save(debate);
    }

    public void create(String title, String content, String thumbnail, User user, Category category) {
        Debate d = new Debate();
        d.setTitle(title);
        d.setContent(content);
        d.setThumbnailImg(thumbnail);
        d.setAuthor(user);
        d.setCategory(category);

        this.debateRepository.save(d);

    }

    public void save(Debate debate) {
        debateRepository.save(debate);
    }

    public List<Debate> findByKeyword(String keyword) {
        return debateRepository.findByKeyword(keyword);
    }

    public void deletereport(Long id) {
        Optional<Debate> debate = debateRepository.findById(id);

        if (debate.isPresent()) {
            debateRepository.deleteById(id);
        } else {
            throw new RuntimeException("게시글이 존재하지 않습니다.");
        }
    }

    public List<Debate> findAllDiscussionsOrderByViewDesc() {
        return debateRepository.findAllByOrderByViewDesc();
    }

    public List<Debate> findAllDiscussionsOrderByCreateDateDesc() {
        return debateRepository.findAllByOrderByCreateDateDesc();
    }

    public List<Debate> getListByUserId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return debateRepository.findAllByAuthor(user);
        } else {
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }


//    //메인 페이지에 최신순으로 게시글 나열
//    public List<Freedcs> findAllDiscussionsOrderByCreateDateDesc() {
//        return freedcsRepository.findAllByOrderByCreateDateDesc();
//    }
}





