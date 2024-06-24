package com.example.coconut.domain.reportReply.entity;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ReportReply extends BaseEntity {

    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    @ManyToOne // table의 관계에서 반드시 작성해주어야함
    private Report report;

    @ManyToOne
    private User author;

    @ManyToMany
    Set<User> voter;

}
