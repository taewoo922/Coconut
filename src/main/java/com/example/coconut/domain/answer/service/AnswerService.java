package com.example.coconut.domain.answer.service;

import com.example.coconut.DataNotFoundException;
import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.answer.repository.AnswerRepository;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public void create(Freedcs freedcs, String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setFreedcs(freedcs);
        this.answerRepository.save(answer);
    }

    public Answer getAnswer(Integer id) {
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()) {
            return answer.get();
        } else {
            throw new DataNotFoundException("answer not found");
        }
    }

    public void vote(Answer answer, User voter) {
        answer.addVoter(voter);

        this.answerRepository.save(answer);
    }
}