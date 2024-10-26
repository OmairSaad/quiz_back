package com.exam.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exam.exam.Models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    public User findByUsername(String name);
}
