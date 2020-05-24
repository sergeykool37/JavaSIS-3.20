package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerItemDTO;
import com.sergeykool37.restApp.data.AnswerRepository;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public List<Answer> findAllAnswer() {
        return (List<Answer>) answerRepository.findAll();
    }

    @Override
    public Answer findByIdAnswer(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new RuntimeException(String
                .format("Не найден вопрос с id %s", id)));
    }

    @Override
    public List<Answer> findByQuestionAnswer(Question question) {
        return answerRepository.findByQuestion(question);
    }

    @Override
    public void saveAnswer(AnswerItemDTO answerDTO,Question question) {
        Answer answer = new Answer();
        answer.setName(answerDTO.answerText);
        answer.setIsCorrect(answerDTO.isCorrect);
        answer.setQuestion(question);
        answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(List<Answer> answersCurrent, int i) {
        answerRepository.delete(answersCurrent.get(i));
    }
}
