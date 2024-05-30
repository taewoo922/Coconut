package com.example.coconut.domain.discussion_Type.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FreedcsForm {

    @NotBlank(message="제목은 필수항목입니다.")
    @Size(max=200, message = "제목을 200자 이하로 입력해주세요.")
    private String title;

    @NotEmpty(message="내용은 필수항목입니다.")
    @Size(max=20000, message = "내용을 20000자 이하로 입력해주세요.")
    private String content;

    @NotBlank(message="카테고리 선택은 필수사항입니다.")
    private String category;

}
