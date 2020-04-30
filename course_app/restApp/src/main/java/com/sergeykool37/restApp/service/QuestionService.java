package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    QuestionsItemDTO createQuestion(QuestionsItemDTO dto);

    void editQuestion(QuestionsItemDTO dto);

    Optional<Question> getByIdQuestion(QuestionsItemDTO dto);

    List<QuestionsItemDTO> returnQestions();

    Iterable<Question> getAll();

    List<Question> findByNameContainingIgnoreCaseQuestion(String search);
}
