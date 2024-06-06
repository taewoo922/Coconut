package com.example.coconut.domain.discussion_Type.service;




import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.category.entity.Category;



import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.user.entity.User;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class FreedcsService {
    private final FreedcsRepository freedcsRepository;

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

<<<<<<< HEAD
    public void free_create(String title, String content, User user){
=======
//    오류때문에 우선 User user 인자 제거함
    public Freedcs free_create(String title, String content, User author){
>>>>>>> 1e4494d (충돌1)
        Freedcs f = new Freedcs();
        f.setTitle(title);
        f.setContent(content);
        f.setAuthor(author); // user 오류나서 우선 주석처리
        this.freedcsRepository.save(f);
//        return f;
    }

    public Page<Freedcs> getList(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Freedcs> spec = search(kw);
        return  this.freedcsRepository.findAll(spec, pageable);
    }

    public void modify(Freedcs freedcs, String title, String content) {
        freedcs.setTitle(title);
        freedcs.setContent(content);
        this.freedcsRepository.save(freedcs);
    }

    public void delete(Freedcs freedcs){
        this.freedcsRepository.delete(freedcs);
    }




//    public void free_create(String title, String content,MultipartFile thumbnail) {
//
//
//        String thumbnailRelPath = "freedcs/" + UUID.randomUUID().toString() + ".jpg";
//        File thumbnailFile = new File(fileDirPath + "/" +thumbnailRelPath);
//
//        try {
//            thumbnail.transferTo(thumbnailFile);
//        } catch( IOException e ) {
//            throw new RuntimeException(e);
//        }
//
//        Freedcs freedcs = Freedcs.builder()
//                .title(title)
//                .content(content)
//                .thumbnailImg(thumbnailRelPath)
////                .category(category)
//                .build();
//        freedcsRepository.save(freedcs);
//    }


    public void vote(Freedcs freedcs, User user) {
        freedcs.getVoter().add(user);
        this.freedcsRepository.save(freedcs);
    }

//    public void vote(Freedcs freedcs, User voter) {
//        freedcs.addVoter(voter);
//
//        this.freedcsRepository.save(freedcs);
//
//    }



}
