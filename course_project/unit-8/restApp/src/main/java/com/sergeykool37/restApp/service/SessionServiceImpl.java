package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.controller.dto.SessionDTO;
import com.sergeykool37.restApp.controller.dto.SessionItemDTO;
import com.sergeykool37.restApp.data.AnswerRepository;
import com.sergeykool37.restApp.data.QuestionRepository;
import com.sergeykool37.restApp.data.SessionRepository;
import com.sergeykool37.restApp.entity.Question;
import com.sergeykool37.restApp.entity.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private final QuestionRepository questionRepository;
    private final SessionRepository sessionRepository;

    public SessionServiceImpl(QuestionRepository questionRepository,
                              SessionRepository sessionRepository) {
        this.questionRepository = questionRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public SessionDTO createSession(SessionItemDTO dto) {
        Session session=new Session();
        session.setFio(dto.name);
        session.setPercent(100.0);
        sessionRepository.save(session);
        return new SessionDTO(sessionRepository.findById(new Long(session.getId()))
                .orElseThrow(() -> new RuntimeException(String
                .format("Не найдена сессия с id %d", session.getId()))
                )
        );
    }

    @Override
    public List<QuestionsItemDTO> returnQestions() {
        List<Question>questions= (List<Question>) questionRepository.findAll();

        return null;
    }
}
