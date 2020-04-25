package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Session;

import java.util.List;

public class AnsweredQuestionDTO {
    public String id;
    public List<AnswerUserDTO> answersList;

    public AnsweredQuestionDTO(){}

}
