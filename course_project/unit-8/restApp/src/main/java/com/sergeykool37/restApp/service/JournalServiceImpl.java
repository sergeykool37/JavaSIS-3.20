package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.data.JournalRepository;
import com.sergeykool37.restApp.entity.Journal;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@Transactional
public class JournalServiceImpl implements JournalService{

    public static  String QUESTIONS_JOURNAL_ID = "questions";

    private final JournalRepository journalRepository;

    public JournalServiceImpl(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    @Override
    public Journal getJournal(String id) {
        return journalRepository.findById(id)
                .orElseThrow(()-> new RuntimeException(String
                        .format("Не найден журнал с id %s",id)));

    }
}
