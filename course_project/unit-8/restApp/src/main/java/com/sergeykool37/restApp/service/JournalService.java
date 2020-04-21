package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.JournalItemDTO;
import com.sergeykool37.restApp.controller.dto.JournalRequestDTO;
import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.entity.Journal;

import java.util.List;

public interface JournalService {
    Journal getJournal(String id);

    List<? extends JournalItemDTO> getJournalRows(String id, JournalRequestDTO req);
}
