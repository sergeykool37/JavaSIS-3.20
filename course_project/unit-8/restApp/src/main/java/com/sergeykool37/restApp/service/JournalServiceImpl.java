package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.JournalItemDTO;
import com.sergeykool37.restApp.controller.dto.JournalRequestDTO;
import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.controller.dto.SessionDTO;
import com.sergeykool37.restApp.data.AnswerRepository;
import com.sergeykool37.restApp.data.JournalRepository;
import com.sergeykool37.restApp.data.QuestionRepository;
import com.sergeykool37.restApp.data.SessionRepository;
import com.sergeykool37.restApp.entity.BaseEntity;
import com.sergeykool37.restApp.entity.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    public static final String QUESTIONS_JOURNAL_ID = "questions";
    public static final String SESSION_JOURNAL_ID = "sessions";
    private final AnswerRepository answerRepository;
    private final JournalRepository journalRepository;
    private final QuestionRepository questionRepository;
    private final SessionRepository sessionRepository;

    public JournalServiceImpl(AnswerRepository answerRepository,
                              JournalRepository journalRepository,
                              QuestionRepository questionRepository, SessionRepository sessionRepository) {
        this.answerRepository = answerRepository;
        this.journalRepository = journalRepository;
        this.questionRepository = questionRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Journal getJournal(String id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String
                        .format("Не найден журнал с id %s", id)));

    }

    @Override
    public List<? extends QuestionsItemDTO> getJournalRows(
            String id, JournalRequestDTO req) {
        List<? extends QuestionsItemDTO> collection;
        switch (id) {
            case QUESTIONS_JOURNAL_ID:
                collection = getCollection(
                        req.search,
                        questionRepository::findByNameContainingIgnoreCase,
                        q -> new QuestionsItemDTO(
                                q,
                                answerRepository.findByQuestion(q)));
                break;
            case SESSION_JOURNAL_ID:
                collection = getCollection(
                        req.search,
                        questionRepository::findByNameContainingIgnoreCase,
                        q -> new QuestionsItemDTO(
                                q,
                                answerRepository.findByQuestion(q)));
                break;
            default:
                throw new RuntimeException(String
                        .format("Не найден журнал с id %s", id));

        }
        return collection;
    }

    private <T extends BaseEntity, U extends JournalItemDTO> List<U> getCollection(
            String search,
            Function<String, List<T>> finder,
            Function<T, U> mapper
    ) {
        return finder.apply(search)
                .stream()
                .map(mapper)
                .collect(Collectors.toList());
    }
}
