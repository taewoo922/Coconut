package com.example.coconut.domain.category.entity;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.domain.report.entity.Report;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class Category extends BaseEntity{
    @NotEmpty(message = "카테고리 이름은 필수 입력 사항입니다.")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Freedcs> freedcs;

    // 기본 생성자
    public Category() {}

    // 인자를 받는 생성자
    public Category(String name) {
        this.name = name;
    }
}
