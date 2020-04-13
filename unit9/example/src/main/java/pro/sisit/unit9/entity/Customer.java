package pro.sisit.unit9.entity;

import lombok.Data;

import javax.persistence.*;
//сущность "покупатель" с атрибутами имя и строка адреса
@Data
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="address")
    private String address;
}
