package com.project.ppp.dao;

import com.project.ppp.entity.Role;

public interface RoleDao extends Dao<Role> {
    Role findByName(String name);
}
