package com.tms.myproject.service.Role;

import com.tms.myproject.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRole();
    Role findRoleByName(String nameRole);
}
