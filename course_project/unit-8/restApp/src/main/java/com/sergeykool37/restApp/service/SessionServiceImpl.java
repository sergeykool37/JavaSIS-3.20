package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.data.AnswerRepository;
import com.sergeykool37.restApp.data.QuestionRepository;
import com.sergeykool37.restApp.entity.Question;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public SessionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<QuestionsItemDTO> returnQestions() {
        List<Question>questions= (List<Question>) questionRepository.findAll();

        return null;
    }
}
