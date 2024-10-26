package com.exam.exam.Services.Impl;

import com.exam.exam.Exceptions.UserBadReq;
import com.exam.exam.Models.Category;
import com.exam.exam.Models.Quiz;
import com.exam.exam.Repository.QuizRepository;
import com.exam.exam.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz getQuiz(Long id) {
        return this.quizRepository.findById(id).orElseThrow(()-> new UserBadReq("No Quiz found with id " + id));
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public void deleteQuiz(Long id) {
           Quiz quiz = this.quizRepository.findById(id).orElseThrow(()-> new UserBadReq("No Quiz found with id " + id));

        // Remove this quiz from its category if it's associated
        if (quiz.getCategory() != null) {
            quiz.getCategory().getQuizzes().remove(quiz);
        }

        this.quizRepository.delete(quiz); // Deleting the q
    }

    @Override
    public List<Quiz> getAllQuiz() {
        return this.quizRepository.findAll();
    }

    @Override
    public List<Quiz> getAllActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getAllActiveQuizzesByCategor(Category category) {
        return this.quizRepository.findByCategoryAndActive(category, true);
    }

}
