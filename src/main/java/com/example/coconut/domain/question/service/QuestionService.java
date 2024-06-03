package com.example.coconut.domain.question.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.question.entity.Question;
import com.example.coconut.domain.question.repository.QuestionRepository;
import com.example.coconut.domain.user.entity.User;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    public List<Question> getList() {
        return this.questionRepository.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> oq = this.questionRepository.findById(id);

        // oq.isPresent() == false
//        !oq.isPresent()
        // oq.isEmpty()
        if ( oq.isEmpty() ) throw new DataNotFoundException("question not found");

        return oq.get();
    }

    public Question create(String title, String content, User user) {
        Question q = new Question();
        q.setTitle(title);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        q.setAuthor(user);
        this.questionRepository.save(q);
        return q;
    }
    public Page<Question> getList(int page, String kw) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

        if ( kw == null || kw.trim().length() == 0) {
            return this.questionRepository.findAll(pageable);
        }

        Specification<Question> spec = search(kw);

        return this.questionRepository.findAll(spec, pageable);
    }

    public void modify(Question question, String title, String content) {
        question.setTitle(title);
        question.setContent(content);
        question.setModifyDate(LocalDateTime.now());
        questionRepository.save(question);
    }

    public void delete(Question question) {
        questionRepository.delete(question);
    }

    public void vote(Question question, User voter) {
        question.addVoter(voter);

        this.questionRepository.save(question);
    }

    private Specification<Question> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Question, User> u1 = q.join("author", JoinType.LEFT);
                Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
                Join<Answer, User> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }
}
