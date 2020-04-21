package com.sergeykool37.restApp.controller.dto;

import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionsItemDTO extends JournalItemDTO{
    public String name;
    public List<AnswerItemDTO> answers;

    public QuestionsItemDTO(Question question, List<Answer> answer) {
        this.id = question.getId().toString();
        this.name = question.getName();
        this.answers = answer.stream()
                .map(AnswerItemDTO::new)
                .collect(Collectors.toList());
    }
}
