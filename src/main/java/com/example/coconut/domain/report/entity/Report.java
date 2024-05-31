package com.example.coconut.domain.report.entity;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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

    private boolean isSecret;  // 비밀글 여부 필드 추가

}
