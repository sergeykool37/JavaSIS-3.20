package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.JournalItemDTO;
import com.sergeykool37.restApp.controller.dto.JournalRequestDTO;
import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.controller.dto.SessionDTO;
import com.sergeykool37.restApp.data.JournalRepository;
import com.sergeykool37.restApp.entity.BaseEntity;
import com.sergeykool37.restApp.entity.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.sergeykool37.restApp.Enum.QUESTIONS_JOURNAL_ID;
import static com.sergeykool37.restApp.Enum.SESSION_JOURNAL_ID;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    @Autowired
    private AnswerServiceImpl answerService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private SessionServiceImpl sessionService;
    @Autowired
    private JournalRepository journalRepository;


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
                        questionService::findByNameContainingIgnoreCaseQuestion,
                        q -> new QuestionsItemDTO(
                                q,
                                answerService.findByQuestionAnswer(q)));
                break;
            case SESSION_JOURNAL_ID:
                collection = sessionService
                        .findByFioContainingIgnoreCaseSession(req.search)
                        .stream()
                        .map(session -> new SessionDTO(sessionService
                                .findByIdSession(new Long(session.getId()))
                        ))
                        .collect(Collectors.toList());
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
