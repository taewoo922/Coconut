package com.example.coconut.domain.answer.repository;

import com.example.coconut.domain.answer.entity.Answer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
//    @Transactional
//    @Modifying
//    @Query(value="ALTER TABLE answer AUTO_INCREMENT = 1", nativeQuery = true)
//    void clearAutoIncrement();

}
