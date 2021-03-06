package com.sergeykool37.restApp.data;

import com.sergeykool37.restApp.entity.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session, Long> {
    List<Session> findByFioContainingIgnoreCase(String search);
}
