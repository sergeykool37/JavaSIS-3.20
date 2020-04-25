package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Session;

import java.time.LocalDateTime;


public class SessionDTO extends QuestionsItemDTO{
    public String name;
    public Double percent;
    public LocalDateTime insertDate;
    public int result;

    public SessionDTO() {
    }

    public SessionDTO(Session session) {
        this.id = session.getId().toString();
        this.name = session.getFio();
        this.percent=session.getPercent();
        this.insertDate=session.getDate();
        this.result= session.getPercent().intValue();

    }
}
