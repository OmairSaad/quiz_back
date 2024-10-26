package com.exam.exam.Repository;

import com.exam.exam.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategRepository extends JpaRepository<Category, Long> {
}
