package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Question;
import com.sergeykool37.restApp.entity.Session;

import java.util.List;

public class SessionItemDTO {
    public String name;
    public List<AnsweredQuestionDTO> questionsList;

    public SessionItemDTO(){}


}
