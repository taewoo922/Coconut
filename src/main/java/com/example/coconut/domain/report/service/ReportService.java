package com.example.coconut.domain.report.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

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
}
