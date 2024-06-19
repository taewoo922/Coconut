package com.example.coconut.domain.answer.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.answer.repository.AnswerRepository;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.discussion_Type.service.DebateService;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final DebateService debateService;

//    User author 오류때문에 임시 삭제
    public Answer f_create(Freedcs freedcs, String content, User author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setFreedcs(freedcs);
        answer.setAuthor(author); //오류때문에 임시 주석
        this.answerRepository.save(answer);
        return answer;
    }

    public Answer d_create(Debate debate, String content, User author, boolean isSupport) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setDebate(debate);
        answer.setAuthor(author);
        answer.setSupport(isSupport);
        this.answerRepository.save(answer);


        // Debate 객체를 업데이트하여 변경 사항을 저장
        // debateService.save(debate) 메소드가 있어야 합니다
        this.debateService.save(debate);

        return answer;
    }

    public Answer getAnswer(Long id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        this.answerRepository.save(answer);
    }

    public void delete(Answer answer){
        this.answerRepository.delete(answer);
    }

    public void vote(Answer answer, User user) {
        answer.getVoter().add(user);
        this.answerRepository.save(answer);
    }

}