package com.sergeykool37.restApp.controller.dto;

public class JournalEntityDTO {
    public String id;
    public String name;
    public int defaultPageSize;

    public JournalEntityDTO(String id, String name, int defaultPageSize) {
        this.id = id;
        this.name = name;
        this.defaultPageSize = defaultPageSize;
    }
}
