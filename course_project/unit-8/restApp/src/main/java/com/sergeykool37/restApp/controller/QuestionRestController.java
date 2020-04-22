package com.sergeykool37.restApp.controller;

import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/question")
public class QuestionRestController {
    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @PostMapping("create")
    public QuestionsItemDTO Create(@RequestBody QuestionsItemDTO dto){
        return questionService.createQuestion(dto);
    }
    @PutMapping("edit")
    public void Edit(@RequestBody QuestionsItemDTO dto){
        questionService.editQuestion(dto);
    }
}
