package com.tms.myproject.repository;

import com.tms.myproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("from Role r where r.name=?1")
    Role findRoleByName(String nameRole);

}
