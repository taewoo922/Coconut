package com.example.coconut.domain.report.entity;

import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Report extends BaseEntity {

    @Column(length = 200) // VARCHAR(200)
    private String title;

    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    @ManyToOne
    private User user; //작성자 정보

    private boolean isSecret;  // 비밀글 여부 필드 추가
}
