package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerItemDTO;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;

import java.util.List;

public interface AnswerService {
    List<Answer> findAllAnswer();

    Answer findByIdAnswer(Long id);

    List<Answer> findByQuestionAnswer(Question question);

    void saveAnswer(AnswerItemDTO answerDTO, Question question);

    void deleteAnswer(List<Answer> answersCurrent, int i);
}
