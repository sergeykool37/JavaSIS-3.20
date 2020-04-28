package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerUserDTO;
import com.sergeykool37.restApp.controller.dto.AnsweredQuestionDTO;
import com.sergeykool37.restApp.controller.dto.SessionItemDTO;
import com.sergeykool37.restApp.data.AnswerRepository;
import com.sergeykool37.restApp.data.SelectedAnswerRepository;
import com.sergeykool37.restApp.data.SessionRepository;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private final SessionRepository sessionRepository;
    private final AnswerRepository answerRepository;
    private final SelectedAnswerRepository selectedAnswerRepository;
    private final SelectedAnswerServiceImpl selectedAnswerService;

    public SessionServiceImpl(SessionRepository sessionRepository,
                              AnswerRepository answerRepository,
                              SelectedAnswerRepository selectedAnswerRepository,
                              SelectedAnswerServiceImpl selectedAnswerService) {
        this.sessionRepository = sessionRepository;
        this.answerRepository = answerRepository;
        this.selectedAnswerRepository = selectedAnswerRepository;
        this.selectedAnswerService = selectedAnswerService;
    }


    @Override
    public String createSession(SessionItemDTO dto) {
        Session session = new Session();
        session.setFio(dto.name);
        List<Answer> answersList = (List) answerRepository.findAll();
        int answerTrue = (int) answersList.stream()
                .filter(answer -> answer.getCorrect())
                .count();
        int TrueAnswerCount = getRightsAnswerCount(dto, session, answersList);
        double result = (double) TrueAnswerCount / answerTrue * 100;
        session.setPercent(result);
        session.setDate(LocalDateTime.now());
        sessionRepository.save(session);
        return String.format("%d", (int) result);
    }

    private int getRightsAnswerCount(SessionItemDTO dto,
                                     Session session,
                                     List<Answer> answersList) {
        int TrueAnswerCount = 0;
        for (AnsweredQuestionDTO question : dto.questionsList) {
            for (AnswerUserDTO answer : question.answersList) {
                if (answer.isSelected) {
                    selectedAnswerService.saveSelectedAnswer(session, answer);
                }
                TrueAnswerCount += (int) answersList
                        .stream()
                        .filter(rightAnswer -> rightAnswer.getId().toString().equals(answer.id))
                        .filter(rightAnswer -> rightAnswer.getCorrect().equals(answer.isSelected)
                                & rightAnswer.getCorrect())
                        .count();
            }
        }
        return TrueAnswerCount;
    }

}
