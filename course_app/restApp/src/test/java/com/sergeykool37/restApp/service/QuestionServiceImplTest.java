package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.data.QuestionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class QuestionServiceImplTest {
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private QuestionRepository questionRepository;

    @Test
    void createQuestion() {
        Assert.assertEquals(1,1);
    }

    @Test

    void editQuestion() {
    }
    @Test
    void returnQestions(){
        Assert.assertEquals(new QuestionsItemDTO().getClass(),questionService.returnQestions().get(0).getClass());
    }
}