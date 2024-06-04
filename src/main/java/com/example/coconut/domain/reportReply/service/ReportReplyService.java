package com.example.coconut.domain.reportReply.service;


import com.example.coconut.DataNotFoundException;

import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.reportReply.repository.ReportReplyRepository;
import com.example.coconut.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportReplyService {

    private final ReportReplyRepository reportReplyRepository;

    public ReportReply create(Report report, String content, User author) {
        ReportReply reportReply = new ReportReply();
        reportReply.setContent(content);
        reportReply.setReport(report);
        reportReply.setAuthor(author);
        this.reportReplyRepository.save(reportReply);
        return reportReply;
    }

    public ReportReply getReportReply(Long id){
        Optional<ReportReply> reportReply = this.reportReplyRepository.findById(id);
        if (reportReply.isPresent()) {
            return reportReply.get();
        } else {
            throw new DataNotFoundException("reportReply not found");
        }
    }

    public void modify(ReportReply reportReply, String content) {
        reportReply.setContent(content);
        this.reportReplyRepository.save(reportReply);
    }

    public void delete(ReportReply reportReply){
        this.reportReplyRepository.delete(reportReply);
    }

    public void vote(ReportReply reportReply, User user) {
        reportReply.getVoter().add(user);
        this.reportReplyRepository.save(reportReply);
    }

}