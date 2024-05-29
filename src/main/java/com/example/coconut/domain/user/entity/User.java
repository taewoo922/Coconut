package com.example.coconut.domain.user.entity;

import com.example.coconut.domain.answer.entity.Answer;
import com.example.coconut.domain.question.entity.Question;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;


import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity {

    @Comment("유저 아이디")
    @Column(unique = true, nullable = false)
    private String username;

    @Comment("비밀번호")
    @Column(nullable = false)
    private String password;

    @Comment("전화번호")
    @Column(unique = true, nullable = false)
    private String phone;

    @Comment("닉네임")
    @Column(unique = true, nullable = false)
    private String nickname;

    @Comment("이메일")
    @Column(unique = true, nullable = false)
    private String email;


}
