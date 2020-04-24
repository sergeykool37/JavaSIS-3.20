package com.sergeykool37.restApp.service;


import com.sergeykool37.restApp.controller.dto.QuestionsItemDTO;
import com.sergeykool37.restApp.controller.dto.SessionDTO;
import com.sergeykool37.restApp.controller.dto.SessionItemDTO;

import java.util.List;

public interface SessionService {
    SessionDTO createSession(SessionItemDTO dto);
    List<QuestionsItemDTO> returnQestions();
}
