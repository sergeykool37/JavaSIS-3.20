package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerUserDTO;
import com.sergeykool37.restApp.controller.dto.AnsweredQuestionDTO;
import com.sergeykool37.restApp.controller.dto.SessionItemDTO;
import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest
class SessionServiceImplTest {
    @Mock
    private AnswerService answerService;

    @InjectMocks
    private SessionServiceImpl sessionService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void createSession() {
        SessionItemDTO sessionItemDTO=new SessionItemDTO();
        sessionItemDTO.name="Ivanov";
        sessionItemDTO.id="1";

        List<AnsweredQuestionDTO> answeredQuestionDTOList= new ArrayList<>();
        AnsweredQuestionDTO answeredQuestionDTO1=new AnsweredQuestionDTO();
        answeredQuestionDTO1.id="1";
        AnswerUserDTO answerUserDTO1=new AnswerUserDTO();
        answerUserDTO1.id="1";
        answerUserDTO1.isSelected=Boolean.TRUE;
        AnswerUserDTO answerUserDTO2=new AnswerUserDTO();
        answerUserDTO2.id="2";
        answerUserDTO2.isSelected=Boolean.FALSE;
        AnswerUserDTO answerUserDTO3=new AnswerUserDTO();
        answerUserDTO3.id="3";
        answerUserDTO3.isSelected=Boolean.TRUE;
        List<AnswerUserDTO> answerUserDTOList1=new ArrayList<>();
        answerUserDTOList1.add(answerUserDTO1);
        answerUserDTOList1.add(answerUserDTO2);
        answerUserDTOList1.add(answerUserDTO3);
        answeredQuestionDTO1.answersList=answerUserDTOList1;
        answeredQuestionDTOList.add(answeredQuestionDTO1);
        sessionItemDTO.questionsList=answeredQuestionDTOList;

        List<Answer> answerList=new ArrayList<>();
        Question question=new Question();
        question.setId(1L);
        question.setName("Question1");
        Answer answer1=new Answer();
        answer1.setIsCorrect(Boolean.TRUE);
        answer1.setId(1L);
        answer1.setQuestion(question);
        answer1.setName("Answer1");
        Answer answer2=new Answer();
        answer2.setIsCorrect(Boolean.FALSE);
        answer2.setId(2L);
        answer2.setName("Answer2");
        answer2.setQuestion(question);
        Answer answer3=new Answer();
        answer3.setIsCorrect(Boolean.TRUE);
        answer3.setId(3L);
        answer3.setName("Answer3");
        answer3.setQuestion(question);
        answerList.add(answer1);
        answerList.add(answer2);
        answerList.add(answer3);

        when(answerService.findAllAnswer()).thenReturn(answerList).thenReturn(answerList);
        sessionService.createSession(sessionItemDTO);

//        java.lang.NullPointerException
//        at com.sergeykool37.restApp.service.SessionServiceImpl.createSession(SessionServiceImpl.java:30)
//        at com.sergeykool37.restApp.service.SessionServiceImplTest.createSession(SessionServiceImplTest.java:88)


    }
}