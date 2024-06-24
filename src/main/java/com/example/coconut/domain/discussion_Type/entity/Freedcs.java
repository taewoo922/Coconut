package com.example.coconut.domain.discussion_Type.entity;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.scrap.entity.Scrap;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Freedcs extends BaseEntity {
    @Column(length = 50) // VARCHAR(50)
    private String title;
    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    private String nickname;

    private String thumbnailImg;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "freedcs", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private User author;

    @ManyToMany
    Set<User> voter;

    @Transient
    private String search;

    private int view;

    @Transient
    private String authorNickname; // 작성자의 닉네임을 임시로 저장하기 위한 필드

    @OneToMany(mappedBy = "freedcs", cascade = CascadeType.REMOVE)
    private List<Scrap> scrapList;
}
