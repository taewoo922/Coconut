package com.example.coconut.domain.discussion_Type.entity;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.category.entity.Category;
import com.example.coconut.domain.reportReply.entity.ReportReply;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    private String title;
    private String content;
    private String nickname;
    private String thumbnailImg;
    private String category;
    private String code;
    private String displayName;

    @OneToMany(mappedBy = "freedcs", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private User author;

    @ManyToMany
    Set<User> voter = new LinkedHashSet<>();





}
