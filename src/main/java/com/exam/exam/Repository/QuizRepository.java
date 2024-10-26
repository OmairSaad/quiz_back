package com.exam.exam.Repository;

import com.exam.exam.Models.Category;
import com.exam.exam.Models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List<Quiz> findByActive(boolean active);
    public  List<Quiz> findByCategoryAndActive(Category category, boolean active);
}
