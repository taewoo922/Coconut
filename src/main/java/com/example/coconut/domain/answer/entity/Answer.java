package com.example.coconut.domain.answer.entity;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.question.entity.Question;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Answer extends BaseEntity {
    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

//    @ManyToOne // table의 관계에서 반드시 작성해주어야함
//    private Question question;

    //Many= Answer, one=Question
    //필수
    @ManyToOne
    private Freedcs freedcs;

    @ManyToOne
    private User author;

    @ManyToMany
    Set<User> voter;
    //HashSet은 순서가 보장이 안됨 LinkedHashSet은 순서가 보장됨


}
