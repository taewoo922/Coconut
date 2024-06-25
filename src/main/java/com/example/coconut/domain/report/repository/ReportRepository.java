package com.example.coconut.domain.report.repository;

import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
 Page<Report> findAll(Pageable pageable);
 Page<Report> findAll(Specification<Report> spec, Pageable pageable);

 List<Report> findAllByAuthorId(Long userId);

 List<Report> findAllByAuthor(User user);


}
