package com.exam.exam.Services;

import com.exam.exam.Models.Category;
import com.exam.exam.Models.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuizService {
    public Quiz addQuiz(Quiz quiz);
    public Quiz getQuiz(Long id);
    public Quiz updateQuiz(Quiz quiz);
    public void deleteQuiz(Long id);
    public List<Quiz> getAllQuiz();

    public List<Quiz> getAllActiveQuizzes();

    public List<Quiz> getAllActiveQuizzesByCategor(Category category);
}
