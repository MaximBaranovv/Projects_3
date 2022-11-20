package com.project.ppp.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "jdbc_role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
}
