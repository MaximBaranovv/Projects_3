package com.project.ppp;

import com.project.ppp.dao.jdbc.JdbcRoleService;
import com.project.ppp.dao.jdbc.JdbcUserService;
import com.project.ppp.entity.Role;
import com.project.ppp.entity.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        JdbcRoleService jdbcRoleService = new JdbcRoleService();
       List<Role> list = jdbcRoleService.findAll();
       System.out.println(list);
    }
}
