package com.sergeykool37.restApp.entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Journal {

    @Id
    private String id;

    @Column
    private String name;

    @Column
    private Long defaultPageSize;

}

