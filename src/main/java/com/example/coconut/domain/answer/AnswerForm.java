package com.example.coconut.domain.answer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerForm {
    @NotBlank(message = "내용은 필수항목입니다.")
    private String content;
    private boolean isSupport;

}