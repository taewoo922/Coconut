package com.example.coconut.domain.answer.service;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.answer.repository.AnswerRepository;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
}