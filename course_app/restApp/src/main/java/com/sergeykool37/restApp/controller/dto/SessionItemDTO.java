package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Question;
import com.sergeykool37.restApp.entity.Session;

import java.util.List;

public class SessionItemDTO {
    public String name;
    public String id;
    public List<AnsweredQuestionDTO> questionsList;

    public SessionItemDTO(){}

    public SessionItemDTO(String name,String id,List<AnsweredQuestionDTO> questionsList){
        this.id=id;
        this.name=name;
        this.questionsList=questionsList;
    }


}
