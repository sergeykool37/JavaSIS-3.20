package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerUserDTO;
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
    private AnswerServiceImpl answerService;
    @Autowired
    private SelectedAnswerRepository selectedAnswerRepository;

    @Override
    public void saveSelectedAnswer(Session session, AnswerUserDTO answer) {
        SelectedAnswer selectedAnswer = new SelectedAnswer();
        selectedAnswer.setAnswer(answerService.findByIdAnswer(new Long(answer.id)));
        selectedAnswer.setSession(session);
        selectedAnswerRepository.save(selectedAnswer);
    }
}
