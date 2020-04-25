package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.SessionItemDTO;

public interface SessionService {
    String createSession(SessionItemDTO dto);
}
