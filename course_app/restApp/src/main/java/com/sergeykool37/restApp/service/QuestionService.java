package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;

import java.util.List;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);

    void editQuestion(QuestionsItemDTO dto);

    public List<QuestionsItemDTO> returnQestions();

}
