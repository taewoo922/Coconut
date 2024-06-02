package com.example.coconut.domain.reportReply.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportReplyForm {
    @NotBlank(message = "내용은 필수항목입니다.")
    private String content;

}
