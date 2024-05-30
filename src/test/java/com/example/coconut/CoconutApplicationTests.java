package com.example.coconut;

import com.example.coconut.domain.answer.repository.AnswerRepository;
import com.example.coconut.domain.question.entity.Question;
import com.example.coconut.domain.question.repository.QuestionRepository;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.reportReply.repository.ReportReplyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CoconutApplicationTests {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ReportReplyRepository reportReplyRepository;


	@Test
	void contextLoads() {
//		Report r1 = new Report();
//		r1.setTitle("질문1");
//		r1.setContent("없어요");
//		this.reportRepository.save(r1);  // 첫번째 질문 저장

//		Report r2 = new Report();
//		r2.setTitle("질문2");
//		r2.setContent("있어요");
//		this.reportRepository.save(r2);  // 첫번째 질문 저장

//		Optional<Report> oq = this.reportRepository.findById(Long.valueOf(2));
//		assertTrue(oq.isPresent());
//		Report q = oq.get();

//		ReportReply a = new ReportReply();
//		a.setContent("네");
//		a.setReport(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
//		this.reportReplyRepository.save(a);

	}
}
