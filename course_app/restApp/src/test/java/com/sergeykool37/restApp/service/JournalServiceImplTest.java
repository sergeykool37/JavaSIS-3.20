package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.data.JournalRepository;

import com.sergeykool37.restApp.entity.Journal;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class JournalServiceImplTest {

    @Autowired
    JournalServiceImpl journalService;

    @Test
    @Transactional
    void testGetJournal(){
        Assert.assertEquals("sessions",journalService.getJournal("sessions").getId());
        Assert.assertEquals("questions",journalService.getJournal("questions").getId());
    }

}