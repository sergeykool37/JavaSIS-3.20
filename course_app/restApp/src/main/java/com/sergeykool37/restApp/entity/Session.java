package com.sergeykool37.restApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Session {
    @Column(name = "fio")
    private String fio;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "percent")
    private Double percent;

    @Column(name="date")
    private LocalDateTime date;

}
