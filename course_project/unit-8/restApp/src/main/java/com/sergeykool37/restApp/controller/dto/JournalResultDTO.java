package com.sergeykool37.restApp.controller.dto;

import java.util.List;

public class JournalResultDTO {
    public int total;
    public List<QuestionsItemDTO> items;

    public JournalResultDTO(int total, List<QuestionsItemDTO> items) {
        this.total = total;
        this.items = items;
    }
}
