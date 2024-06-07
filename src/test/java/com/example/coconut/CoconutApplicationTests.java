package com.example.coconut;

import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.reportReply.repository.ReportReplyRepository;
import com.example.coconut.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CoconutApplicationTests {

	@Autowired
	private ReportRepository reportRepository;

	@Autowired
	private ReportService reportService;

	@Autowired
	private ReportReplyRepository reportReplyRepository;

	@Autowired
	private FreedcsService freedcsService;


//	@Test
//	void contextLoads() {
//		for (int i = 1; i <= 300; i++) {
//			String subject = String.format("테스트 데이터입니다:[%03d]", i);
//			String content = "내용무";
//			this.reportService.create(subject, content, null, "자유토론");
//		}

	@Autowired
	private FreedcsRepository freedcsRepository;

//	@Test
//	void contextLoads() {
//		for (int i = 1; i <= 300; i++) {
//			String subject = String.format("테스트 데이터입니다:[%03d]", i);
//			String content = "내용무";
//			this.reportService.create(subject, content, null);
//		}

	/*@Test
	void contextLoads() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.reportService.create(subject, content, null, "자유토론");
		}*/
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

	@Test
	void contextLoads() {
		for (int i = 1; i <= 5; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			String thumbnailImg = "freedcs/" + "[사진이름]" + ".jpg";

//			[사진이름]자리에 본인 폴더 안에있는 사진 이름 입력
			this.freedcsService.create(subject, content, thumbnailImg);
		}
	}
}
