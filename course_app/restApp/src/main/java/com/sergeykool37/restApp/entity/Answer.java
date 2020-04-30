package com.sergeykool37.restApp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Answer extends BaseEntity {

    @Column
    private String name;

    @JoinColumn(name = "question_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @Column
    private Boolean isCorrect;


}
