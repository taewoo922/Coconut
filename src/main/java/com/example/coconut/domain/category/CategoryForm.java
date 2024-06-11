package com.example.coconut.domain.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class CategoryForm {
    @NotBlank(message = "카테고리 이름은 필수 항목입니다.")
    private String name;

}
