package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);
}
