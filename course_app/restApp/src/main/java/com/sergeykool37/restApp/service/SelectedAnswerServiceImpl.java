package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerUserDTO;
import com.sergeykool37.restApp.data.AnswerRepository;
import com.sergeykool37.restApp.data.SelectedAnswerRepository;
import com.sergeykool37.restApp.entity.SelectedAnswer;
import com.sergeykool37.restApp.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SelectedAnswerServiceImpl implements SelectedAnswerService {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private SelectedAnswerRepository selectedAnswerRepository;

    @Override
    public void saveSelectedAnswer(Session session, AnswerUserDTO answer) {
        SelectedAnswer selectedAnswer = new SelectedAnswer();
        selectedAnswer.setAnswer(answerRepository.findById(new Long(answer.id))
                .orElseThrow(() -> new RuntimeException(String
                        .format("Не найден вопрос с id %s", answer.id))));
        selectedAnswer.setSession(session);
        selectedAnswerRepository.save(selectedAnswer);
    }
}
