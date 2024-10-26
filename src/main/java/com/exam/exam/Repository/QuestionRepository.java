package com.exam.exam.Repository;

import com.exam.exam.Models.Question;
import com.exam.exam.Models.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Page<Question> findByquiz(Quiz quiz, Pageable pageable);
}
