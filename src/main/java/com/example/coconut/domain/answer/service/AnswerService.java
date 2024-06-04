package com.example.coconut.domain.answer.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.answer.repository.AnswerRepository;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public Answer create(Freedcs freedcs, String content, User author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setFreedcs(freedcs);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
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