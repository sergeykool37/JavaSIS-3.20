package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerUserDTO;
import com.sergeykool37.restApp.controller.dto.AnsweredQuestionDTO;
import com.sergeykool37.restApp.controller.dto.SessionItemDTO;
import com.sergeykool37.restApp.data.SessionRepository;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private SelectedAnswerServiceImpl selectedAnswerService;

    @Override
    public String createSession(SessionItemDTO dto) {
        Session session = new Session();
        session.setFio(dto.name);
        List<Answer> answersList = answerService.findAllAnswer();
        int answerTrue = dto.questionsList.size();
        double TrueAnswerCount = getRightsAnswerCount(dto, session, answersList);
        Double result = (Double) TrueAnswerCount / answerTrue * 100;
        session.setPercent(result);
        session.setDate(LocalDateTime.now());
        sessionRepository.save(session);
        return result.toString();
    }

    @Override
    public List<Session> findByFioContainingIgnoreCaseSession(String search) {
        return sessionRepository.findByFioContainingIgnoreCase(search);
    }

    @Override
    public Session findByIdSession(Long id) {
        return sessionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.
                        format("Не найдена сессия с id %s"
                                , id.toString())));
    }

    private double getRightsAnswerCount(SessionItemDTO dto,
                                        Session session,
                                        List<Answer> answersList) {
        int TrueAnswerCount = 0;
        for (AnsweredQuestionDTO question : dto.questionsList) {
            int n = question.answersList.size();//количество вариантов для ответа
            int m = (int) answersList
                    .stream()
                    .filter(answer -> answer.getIsCorrect() == Boolean.TRUE &
                            answer.getQuestion().getId().toString().equals(question.id))
                    .count();//количество верных вариантов
            int k = 0;//количество выбранных верных вариантов ответа
            int w = 0;//количество выбранных неверных вариантов ответа
            for (AnswerUserDTO answer : question.answersList) {
                if (answer.isSelected) {
                    if (answer.isSelected == answersList.stream()
                            .filter(answer1 -> answer1.getId().toString().equals(answer.id))
                            .findAny()
                            .orElseThrow(() -> new RuntimeException(String.format("Не найден ответ с  id %s", answer.id)))
                            .getIsCorrect()) {
                        k += 1;
                    } else {
                        w += 1;
                    }
                    ;
                    selectedAnswerService.saveSelectedAnswer(session, answer);
                }
            }
            if (m != n) {
                TrueAnswerCount += (double) Math.max(0, k / m - w / (n - m));
            } else {
                TrueAnswerCount += (double) Math.max(0, k / m);
            }
        }
        return TrueAnswerCount;
    }
}
