package com.portfolio.boatstation.repositories.security;


import com.portfolio.boatstation.entities.security.Role;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

@org.springframework.stereotype.Repository
public interface RoleRepository extends Repository<Role, Long> {
    @Query(value = "select * from _user_role where name = :roleName", nativeQuery = true)
    Optional<Role> getRoleByRoleName(String roleName);

    @Query(value = "insert into _user_role(name) values (:roleName)", nativeQuery = true)
    @Modifying
    void saveNewRole(String roleName);
}
