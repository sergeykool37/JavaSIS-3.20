package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerItemDTO;
import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.data.AnswerRepository;
import com.sergeykool37.restApp.data.QuestionRepository;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);

        questionRepository.save(question);

        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setIsCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);

            answerRepository.save(answer);
        }
        return new QuestionsItemDTO(question,
                answerRepository.findByQuestion(question));
    }

    @Override
    public void editQuestion(QuestionsItemDTO dto) {
        Question question = questionRepository.findById(new Long(dto.id))
                .orElseThrow(() -> new RuntimeException(String
                        .format("Не найден вопрос с id %s", dto.id)));
        question.setName(dto.name);
        answerRepository.findByQuestion(question);
        List<Answer> answersCurrent = answerRepository.findByQuestion(question);

        for (int i = 0; i < answersCurrent.size(); i++) {
            if (answersCurrent
                    .get(i)
                    .getQuestion()
                    .getId() == question.getId()) {
                answerRepository.delete(answersCurrent.get(i));
            }
        }

        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setIsCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);
            answerRepository.save(answer);
        }
    }

    @Override
    public List<QuestionsItemDTO> returnQestions() {
        List<Question>questions=(List<Question>)questionRepository.findAll();
         return questions
                 .stream()
                 .map(question -> new QuestionsItemDTO(question,answerRepository
                         .findByQuestion(question)))
                 .collect(Collectors.toList());

    }

}
