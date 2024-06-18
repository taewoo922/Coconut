package com.example.coconut.domain.discussion_Type.entity;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.category.entity.Category;
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
public class Debate extends BaseEntity {
    @Column(length = 50) // VARCHAR(50)
    private String title;
    @Column(columnDefinition = "TEXT") // TEXT
    private String content;

    private String nickname;

    private String thumbnailImg;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    @OneToMany(mappedBy = "debate", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @OneToMany(mappedBy = "debate", cascade = CascadeType.ALL)
    private List<Answer> opposingAnswers;

    @OneToMany(mappedBy = "debate", cascade = CascadeType.ALL)
    private List<Answer> supportingAnswers;


    @ManyToOne
    private User author;

    @ManyToMany
    Set<User> voter = new LinkedHashSet<>();

    @Transient
    private String search;

}
