package com.example.coconut.domain.reportReply.service;


import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.reportReply.repository.ReportReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportReplyService {

    private final ReportReplyRepository reportReplyRepository;

    public void create(Report report, String content){
        ReportReply reportReply = new ReportReply();
        reportReply.setContent(content);
        reportReply.setReport(report);
        this.reportReplyRepository.save(reportReply);
    }
}
