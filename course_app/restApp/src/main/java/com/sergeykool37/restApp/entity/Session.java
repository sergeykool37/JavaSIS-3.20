package com.sergeykool37.restApp.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Session {
    @Column
    private String fio;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Double percent;

    @Column
    private LocalDateTime date;

}
