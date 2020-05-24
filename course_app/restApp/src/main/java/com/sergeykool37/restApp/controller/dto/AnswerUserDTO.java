package com.sergeykool37.restApp.controller.dto;

public class AnswerUserDTO {
    public String id;
    public boolean isSelected;

    public AnswerUserDTO(){}

    public AnswerUserDTO(String id,boolean isSelected){
        this.id=id;
        this.isSelected=isSelected;
    }
}
