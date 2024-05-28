package com.example.coconut.domain.answer.entity;

import com.example.coconut.domain.question.entity.Question;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Answer extends BaseEntity {
    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    @ManyToOne // table의 관계에서 반드시 작성해주어야함
    private Question question;

}
