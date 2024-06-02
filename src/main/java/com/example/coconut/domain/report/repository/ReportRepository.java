package com.example.coconut.domain.report.repository;

import com.example.coconut.domain.report.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
 Page<Report> findAll(Pageable pageable);
 Page<Report> findAll(Specification<Report> spec, Pageable pageable);
}
