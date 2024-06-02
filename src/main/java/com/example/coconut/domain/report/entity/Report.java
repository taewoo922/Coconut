package com.example.coconut.domain.report.entity;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseEntity {

    @Column(length = 50) // VARCHAR(50)
    private String title;

    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    @OneToMany(mappedBy = "report", cascade = CascadeType.REMOVE)
    private List<ReportReply> replyList;

    @ManyToOne
    private User author;

    @ManyToMany
    Set<User> voter;


}
