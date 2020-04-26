package com.sergeykool37.restApp.controller;

import com.sergeykool37.restApp.controller.dto.*;
import com.sergeykool37.restApp.service.JournalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/journal")
public class JournalRestController {


    private final JournalService journalService;

    public JournalRestController( JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("{id}")
    public JournalEntityDTO getJournalEntity(@PathVariable String id){
        return new JournalEntityDTO(journalService.getJournal(id));

    }

    @PutMapping("{id}/rows")
    public JournalResultDTO GetRows(@PathVariable String id,@RequestBody JournalRequestDTO req){
        List<? extends JournalItemDTO> collect=
                journalService.getJournalRows(id,req);

        return new JournalResultDTO(collect.size(),collect);
    }
}
