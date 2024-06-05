package com.example.coconut.domain.report.service;

import com.example.coconut.DataNotFoundException;

import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.reportReply.entity.ReportReply;
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

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

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
                        cb.like(u2.get("username"), "%" + kw + "%"));   // 답변 작성자
            }
        };
    }

    public List<Report> getList(){
        return this.reportRepository.findAll();
    }

    public Report getReport(Long id){
        Optional<Report> report = this.reportRepository.findById(id);
        if (report.isPresent()) {
            return report.get();
        } else {
            throw new DataNotFoundException("report not found");
        }
    }

    public Report create(String title, String content, User user){
        Report r = new Report();
        r.setTitle(title);
        r.setContent(content);
        r.setAuthor(user);
        this.reportRepository.save(r);
        return r;
    }

    public Page<Report> getList(int page, String kw){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        Specification<Report> spec = search(kw);
        return  this.reportRepository.findAll(spec, pageable);
    }

    public void modify(Report report, String title, String content) {
        report.setTitle(title);
        report.setContent(content);
        this.reportRepository.save(report);
    }

    public void delete(Report report){
        this.reportRepository.delete(report);
    }

    public void vote(Report report, User user){
        report.getVoter().add(user);
        this.reportRepository.save(report);
    }

    public List<Report> getTop5ReportsByVoterCount() {
        Pageable topFive = PageRequest.of(0, 5);
        return reportRepository.findTop5ByOrderByVoterCountDesc(topFive);
    }
}
