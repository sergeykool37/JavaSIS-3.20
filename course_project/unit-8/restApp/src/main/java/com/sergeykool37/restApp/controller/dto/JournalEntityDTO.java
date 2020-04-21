package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Journal;
import com.sergeykool37.restApp.service.JournalServiceImpl;

public class JournalEntityDTO {
    public String id;
    public String name;
    public Long defaultPageSize;

    public JournalEntityDTO(Journal journal) {
        this.id = journal.getId();
        this.name = journal.getName();
        this.defaultPageSize = journal.getDefaultPageSize();
    }
}
