package com.project.ppp;

import com.project.ppp.dao.RoleDao;
import com.project.ppp.dao.jdbc.JdbcRoleDao;
import com.project.ppp.entity.Role;
import configuration.jdbc.ConnectionSupplierJdbc;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        RoleDao roleDao = new JdbcRoleDao(new ConnectionSupplierJdbc());
        List<Role> roles = roleDao.findAll();
        for (Role role: roles) {
            System.out.println(role);
        }
    }
}
