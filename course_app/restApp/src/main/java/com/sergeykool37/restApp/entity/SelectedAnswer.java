package com.sergeykool37.restApp.entity;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@Entity
@Data
public class SelectedAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JoinColumn(name = "answer_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @JoinColumn(name = "session_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

}
