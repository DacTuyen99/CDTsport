package com.CDTsport.CDTsport.repository;


import com.CDTsport.CDTsport.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByName(String role);

}
