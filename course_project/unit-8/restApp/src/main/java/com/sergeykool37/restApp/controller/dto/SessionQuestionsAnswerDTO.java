package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.SelectedAnswer;

import java.util.List;

public class SessionQuestionsAnswerDTO {
    public String id;
    public Boolean isSelected;

    public SessionQuestionsAnswerDTO(){}

    public SessionQuestionsAnswerDTO(SelectedAnswer selectedAnswer, List<Answer> answers) throws Exception {
        id=selectedAnswer.getId().toString();
        isSelected=answers.stream()
                .map(AnswerItemDTO::new)
                .filter(answerItemDTO -> answerItemDTO.id.equals(selectedAnswer.getAnswer().toString()))
                .findAny().orElseThrow(() -> new RuntimeException(String
                        .format("Не найден answer с id %s", id))).isCorrect;
    }

}
