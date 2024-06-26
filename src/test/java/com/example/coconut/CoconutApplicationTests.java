package com.example.coconut;

import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.category.repository.CategoryRepository;
import com.example.coconut.domain.category.service.CategoryService;
import com.example.coconut.domain.discussion_Type.repository.DebateRepository;
import com.example.coconut.domain.discussion_Type.repository.FreedcsRepository;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.discussion_Type.service.FreedcsService;
import com.example.coconut.domain.report.repository.ReportRepository;
import com.example.coconut.domain.report.service.ReportService;
import com.example.coconut.domain.reportReply.repository.ReportReplyRepository;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.domain.user.repository.UserRepository;
import com.example.coconut.domain.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
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

	@Autowired
	private FreedcsRepository freedcsRepository;

	@Autowired
	private DebateService debateService;

	@Autowired
	private DebateRepository debateRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	@DisplayName("report 데이터 저장")
	void reportTest() {
		User user = userService.getUserByUsername("test");
		for (int i = 1; i <= 300; i++) {
			String title = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
			this.reportService.create(title, content, user, "자유토론", true);
		}
	}








	@Test
	@DisplayName("토론 데이터 저장")
	void freeDcsTest() {
		for (int i = 1; i <= 300; i++) {
			String subject = String.format("테스트 데이터입니다:[%03d]", i);
			String content = "내용무";
//			String thumbnailImg = "freedcs/" + "[사진이름]" + ".jpg";
			String thumbnailImg = "freedcs/" + "cf053209-e7d4-4167-a28e-5366090fed61" + ".jpg";

//			[사진이름]자리에 본인 폴더 안에있는 사진 이름 입력

			User user = userService.getUserByUsername("user");
			Category category = categoryService.getCategoryById((long)1);

			this.debateService.create(subject, content, thumbnailImg, user, category);
			this.freedcsService.create(subject, content, thumbnailImg, user, category);
		}
	}
}

