package com.project.ppp.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.project.ppp.converter.DateStringConverter;
import com.project.ppp.converter.StringDateConverter;
import lombok.Data;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "jdbc_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @JsonSerialize(using = DateStringConverter.class)
    @JsonDeserialize(using = StringDateConverter.class)
    @Column(name = "birthday")
    private Date birthday;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
