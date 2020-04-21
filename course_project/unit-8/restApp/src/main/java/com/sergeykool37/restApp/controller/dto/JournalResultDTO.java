package com.sergeykool37.restApp.controller.dto;

import java.util.List;

public class JournalResultDTO {
    public int total;
    public List<? extends JournalItemDTO> items;

    public JournalResultDTO(int total, List<? extends JournalItemDTO> items) {
        this.total = total;
        this.items = items;
    }
}
