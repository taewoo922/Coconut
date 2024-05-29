package com.example.coconut.domain.discussion_Type.entity;

import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;


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
}
