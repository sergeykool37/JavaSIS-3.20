package com.sergeykool37.restApp.data;

import com.sergeykool37.restApp.entity.Journal;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal,String> {

}
