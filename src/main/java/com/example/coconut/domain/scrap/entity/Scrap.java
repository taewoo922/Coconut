package com.example.coconut.domain.scrap.entity;

import com.example.coconut.domain.discussion_Type.entity.Debate;
import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.user.entity.User;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@SuperBuilder
@ToString
public class Scrap extends BaseEntity {

    @ManyToOne
    private Freedcs freedcs;

    @ManyToOne
    private Debate debate;

    private String thumbnailImg;
    private String title;
    private String content;

    @ManyToOne
    private User author;

}
