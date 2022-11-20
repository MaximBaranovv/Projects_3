package com.project.ppp.dao;

import com.project.ppp.entity.User;

import java.util.List;

public interface UserDao extends Dao<User> {
    User findByLogin(String login);

    User findByEmail(String email);
}
