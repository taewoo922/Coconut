package com.example.coconut.domain.answer.repository;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.discussion_Type.entity.Debate;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByDebateAndIsSupport(Debate debate, boolean b);

//    @Transactional
//    @Modifying
//    @Query(value="ALTER TABLE answer AUTO_INCREMENT = 1", nativeQuery = true)
//    void clearAutoIncrement();

}
