package com.example.coconut.domain.category.entity;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Category extends BaseEntity{
    private String code;
    private String displayName;
}