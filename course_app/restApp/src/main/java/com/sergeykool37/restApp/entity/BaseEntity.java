package com.sergeykool37.restApp.entity;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    @Column
    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
