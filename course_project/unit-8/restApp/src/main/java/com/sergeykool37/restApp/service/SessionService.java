package com.sergeykool37.restApp.service;


import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;

import java.util.List;

public interface SessionService {
    List<QuestionsItemDTO> returnQestions();
}
