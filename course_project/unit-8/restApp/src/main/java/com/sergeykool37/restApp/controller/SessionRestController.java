package com.sergeykool37.restApp.controller;


import com.sergeykool37.restApp.controller.dto.*;
import com.sergeykool37.restApp.service.QuestionService;
import com.sergeykool37.restApp.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SessionRestController {
    private final SessionService sessionService;

    private final QuestionService questionService;

    public SessionRestController(SessionService sessionService, QuestionService questionService) {
        this.sessionService = sessionService;
        this.questionService = questionService;
    }

    @PostMapping("session")
    public String create(@RequestBody SessionItemDTO dto){
        System.out.println(dto);
        System.out.println(dto.name);
        System.out.println(dto.questionsList);
        for (AnsweredQuestionDTO question:dto.questionsList){
            System.out.println(question.id);
            for (AnswerUserDTO answer:question.answersList){
                System.out.println(answer.isSelected);
            }
        }
        sessionService.createSession(dto);
        return "100";
    }

    @GetMapping("session/questions-new")
    public List<QuestionsItemDTO> GetQuestionsNew(){
        System.out.println(questionService.returnQestions());
        return questionService.returnQestions();
    }
}
