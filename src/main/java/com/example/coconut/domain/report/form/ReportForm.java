package com.example.coconut.domain.report.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportForm {
    @NotBlank(message="제목은 필수항목입니다.")
    @Size(max=200)
    private String title;

    @NotBlank(message="내용은 필수항목입니다.")
    @Size(max = 500)
    private String content;

    @NotBlank(message="카테고리선택은 필수항목입니다.")
    private String category;

    private boolean secret; // 비밀글 체크박스
}
