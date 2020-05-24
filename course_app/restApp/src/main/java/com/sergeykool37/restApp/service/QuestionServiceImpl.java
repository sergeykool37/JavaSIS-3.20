package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerItemDTO;
import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.data.QuestionRepository;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerServiceImpl answerService;

    @Override
    public QuestionsItemDTO createQuestion(QuestionsItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);

        questionRepository.save(question);

        for (AnswerItemDTO answerDTO : dto.answers) {
            answerService.saveAnswer(answerDTO, question);
        }
        return new QuestionsItemDTO(question,
                answerService.findByQuestionAnswer(question));
    }

    @Override
    public void editQuestion(QuestionsItemDTO dto) {
        Question question = getByIdQuestion(dto)
                .orElseThrow(() -> new RuntimeException(String
                        .format("Не найден вопрос с id %s", dto.id)));
        question.setName(dto.name);
        List<Answer> answersCurrent = answerService.findByQuestionAnswer(question);

        for (int i = 0; i < answersCurrent.size(); i++) {
            if (answersCurrent
                    .get(i)
                    .getQuestion()
                    .getId() == question.getId()) {
                answerService.deleteAnswer(answersCurrent, i);
            }
        }

        for (AnswerItemDTO answerDTO : dto.answers) {
            answerService.saveAnswer(answerDTO, question);
        }
    }

    @Override
    public Optional<Question> getByIdQuestion(QuestionsItemDTO dto) {
        return questionRepository.findById(new Long(dto.id));
    }

    @Override
    public List<QuestionsItemDTO> returnQestions() {
        List<Question> questions = (List<Question>) getAll();
        return questions
                .stream()
                .map(question -> new QuestionsItemDTO(question, answerService.findByQuestionAnswer(question)))
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Question> getAll() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> findByNameContainingIgnoreCaseQuestion(String search) {
        return questionRepository.findByNameContainingIgnoreCase(search);
    }

}
