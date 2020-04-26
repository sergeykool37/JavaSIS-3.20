package com.sergeykool37.restApp.entity;

import javax.persistence.*;

@Entity
public class Answer extends BaseEntity{

    @Column
    private String name;

    @JoinColumn(name="question_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @Column
    private Boolean isCorrect;

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
