package com.example.coconut.domain.report.repository;

import com.example.coconut.domain.report.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ReportRepository extends JpaRepository<Report, Long> {


}
