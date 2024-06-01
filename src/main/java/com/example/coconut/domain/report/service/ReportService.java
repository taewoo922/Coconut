package com.example.coconut.domain.report.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Report create(String title, String content, User user){
        Report r = new Report();
        r.setTitle(title);
        r.setContent(content);
        r.setAuthor(user);
        this.reportRepository.save(r);
        return r;
    }

    public Page<Report> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return  this.reportRepository.findAll(pageable);
    }
}
