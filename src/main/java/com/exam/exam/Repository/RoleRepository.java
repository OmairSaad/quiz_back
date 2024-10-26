package com.exam.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.exam.Models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long>{
    
}
