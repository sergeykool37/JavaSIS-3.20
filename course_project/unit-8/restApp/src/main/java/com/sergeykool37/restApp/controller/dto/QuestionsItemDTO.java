package com.sergeykool37.restApp.controller.dto;

public class QuestionsItemDTO {
    public String id;
    public String name;
    public int answersCount;

    public QuestionsItemDTO(String id, String name, int answersCount) {
        this.id = id;
        this.name = name;
        this.answersCount = answersCount;
    }
}
