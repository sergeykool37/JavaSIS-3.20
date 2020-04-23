package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Session;


public class SessionDTO extends JournalItemDTO {
    public String fio;
    public Double percent;

    public SessionDTO() {
    }

    public SessionDTO(Session session) {
        this.id = session.getId().toString();
        this.fio = session.getFio();
    }
}
