package com.sergeykool37.restApp.data;

import com.sergeykool37.restApp.entity.Answer;
import com.sergeykool37.restApp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer,Long>  {

    List<Answer> findByQuestion(Question question);

    List<Answer> findByNameAndQuestion_id(String name,Long questiond_id);
}
