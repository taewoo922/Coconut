package com.example.coconut.domain.category.entity;

import com.example.coconut.domain.discussion_Type.entity.Freedcs;
import com.example.coconut.global.jpa.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Freedcs> freedcsList;
}
