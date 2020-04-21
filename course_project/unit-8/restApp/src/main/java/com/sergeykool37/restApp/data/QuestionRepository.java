package com.sergeykool37.restApp.data;

import com.sergeykool37.restApp.entity.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {

    List<Question> findByNameContainingIgnoreCase(String search);
}
