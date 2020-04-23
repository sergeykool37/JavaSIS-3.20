package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Question;
import com.sergeykool37.restApp.entity.Session;

import java.util.List;

public class SessionItemDTO {
    public String id;
    public String name;
    public List<QuestionsItemDTO> questions;

    public SessionItemDTO(){}

    public SessionItemDTO(List<QuestionsItemDTO> questions, Session session){
        this.id=session.getId().toString();
        this.name=session.getFio();
        this.questions=questions;
    }
}
