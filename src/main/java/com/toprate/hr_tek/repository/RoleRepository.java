package com.toprate.hr_tek.repository;

import com.toprate.hr_tek.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    Role findRoleByRoleName(String name);
}
