package com.sergeykool37.restApp.controller;


import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class SessionRestController {

    private final QuestionService questionService;

    public SessionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("session")
    public String create(@RequestBody QuestionsItemDTO dto){
        return "100";
    }

    @GetMapping("questions-new")
    public List<QuestionsItemDTO> GetQestionsNew(){
        return null;
//        return questionService.returnQestions();
    }
}
