package com.example.coconut.domain.reportReply.service;


import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.reportReply.repository.ReportReplyRepository;
import com.example.coconut.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}