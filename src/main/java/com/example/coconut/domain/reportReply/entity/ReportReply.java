package com.example.coconut.domain.reportReply.entity;

import com.example.coconut.domain.question.entity.Question;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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

}
