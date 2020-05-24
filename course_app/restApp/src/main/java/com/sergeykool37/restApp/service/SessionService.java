package com.sergeykool37.restApp.service;

import com.sergeykool37.restApp.controller.dto.SessionItemDTO;
import com.sergeykool37.restApp.entity.Session;

import java.util.List;

public interface SessionService {
    String createSession(SessionItemDTO dto);

    List<Session> findByFioContainingIgnoreCaseSession(String search);

    Session findByIdSession(Long id);
}
