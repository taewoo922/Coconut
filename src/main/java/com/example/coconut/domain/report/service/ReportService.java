package com.example.coconut.domain.report.service;

import com.example.coconut.DataNotFoundException;

import com.example.coconut.UnauthorizedAccessException;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.repository.UserRepository;
import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;

    private Specification<Report> search(String kw) {
        return new Specification<>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Report> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
                query.distinct(true);  // 중복을 제거
                Join<Report, User> u1 = q.join("author", JoinType.LEFT);
                Join<Report, ReportReply> a = q.join("replyList", JoinType.LEFT);
                Join<ReportReply, User> u2 = a.join("author", JoinType.LEFT);
                return cb.or(cb.like(q.get("title"), "%" + kw + "%"), // 제목
                        cb.like(q.get("content"), "%" + kw + "%"),      // 내용
                        cb.like(u1.get("username"), "%" + kw + "%"),    // 질문 작성자
                        cb.like(a.get("content"), "%" + kw + "%"),      // 답변 내용
                        cb.like(u2.get("username"), "%" + kw + "%"),    // 답변 작성자
                        cb.like(q.get("category"), "%" + kw + "%"));    // 카테고리
            }
        };
    }

    public List<Report> getList(){
        return this.reportRepository.findAll();
    }

    public Report getReport(Long id) {
        Optional<Report> report = this.reportRepository.findById(id);
        if (report.isPresent()) {
            Report reportEntity = report.get();
            User author = reportEntity.getAuthor(); // 작성자 정보 가져오기
            String nickname = author.getNickname(); // 작성자의 닉네임 가져오기
            reportEntity.setAuthorNickname(nickname); // Report 엔티티에 작성자의 닉네임 설정

            // Check if the report is secret and the current user is not the author or an admin
            if (reportEntity.isSecret() && !isCurrentUserAuthorOrAdmin(reportEntity)) {
                throw new UnauthorizedAccessException("게시물을 열람하실 수 없습니다.");
            }
            return reportEntity;
        } else {
            throw new DataNotFoundException("게시물을 찾을 수 없습니다.");
        }
    }

    private boolean isCurrentUserAuthorOrAdmin(Report report) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));

        return currentUsername.equals(report.getAuthor().getUsername()) || isAdmin;
    }

    public Report create(String title, String content, User user, String category, boolean isSecret){
        Report r = new Report();
        r.setTitle(title);
        r.setContent(content);
        r.setAuthor(user);
        r.setCategory(category);
        r.setSecret(isSecret);
        this.reportRepository.save(r);
        return r;
    }

    public Page<Report> getList(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Report> spec = search(kw);
        Page<Report> reports = this.reportRepository.findAll(spec, pageable);
        // 각 Report 객체에 authorNickname 설정
        reports.forEach(report -> {
            if (report.getAuthor() != null) {
                report.setAuthorNickname(report.getAuthor().getNickname());
            } else {
                report.setAuthorNickname("Unknown");
            }
        });

        return reports;
    }

    public void modify(Report report, String title, String content, String category, boolean isSecret) {
        report.setTitle(title);
        report.setContent(content);
        report.setCategory(category);
        report.setSecret(isSecret);
        this.reportRepository.save(report);
    }

    public void delete(Report report){
        this.reportRepository.delete(report);
    }

    public List<Report> getListByUserId(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return reportRepository.findAllByAuthor(user);
        } else {
            throw new DataNotFoundException("User not found with id: " + id);
        }
    }


    public void deletereport(Long id) {

        Optional<Report> question = reportRepository.findById(id);

        if (question.isPresent()) {
            reportRepository.deleteById(id);
        } else {
            throw new RuntimeException("질문이 존재하지 않습니다.");
        }
    }

}
