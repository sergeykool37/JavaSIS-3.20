package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.AnswerUserDTO;
import com.sergeykool37.restApp.entity.Session;

public interface SelectedAnswerService {
    public void saveSelectedAnswer(Session session, AnswerUserDTO answer);
}

