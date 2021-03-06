package com.sergeykool37.restApp;

import com.sergeykool37.restApp.controller.dto.JournalEntityDTO;
import com.sergeykool37.restApp.data.JournalRepository;
import com.sergeykool37.restApp.entity.Journal;
import com.sergeykool37.restApp.service.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

import static com.sergeykool37.restApp.Enum.QUESTIONS_JOURNAL_ID;
import static com.sergeykool37.restApp.Enum.SESSION_JOURNAL_ID;

@SpringBootApplication
public class RestAppApplication {
	@Autowired
	private JournalRepository journalRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}
	@PostConstruct
	private void InitData(){
		Journal journal=new Journal();
		journal.setId(QUESTIONS_JOURNAL_ID);
		journal.setName("Вопросы");
		journal.setDefaultPageSize(15L);
		journalRepository.save(journal);

		Journal journalSession=new Journal();
		journal.setId(SESSION_JOURNAL_ID);
		journal.setName("Сессии");
		journal.setDefaultPageSize(15L);
		journalRepository.save(journal);

	}
}
