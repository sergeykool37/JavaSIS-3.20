package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Answer;

public class AnswerItemDTO {
    public String id;
    public String answerText;
    public Boolean isCorrect;

    public AnswerItemDTO() {

    }

    public AnswerItemDTO(Answer answer) {
        id = answer.getId().toString();
        answerText = answer.getName();
        isCorrect = answer.getCorrect();
    }
}
