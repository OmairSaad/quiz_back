package com.exam.exam.Services;

import com.exam.exam.Models.Question;
import com.exam.exam.Models.Quiz;
import com.exam.exam.Payloads.QuestionResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface QuestionService {
    public Question addQuestion(Question question);
    public Question getQuestion(Long id);
    public Question updateQuestion(Question question);
    public void deleteQuestion(Long id);
    public List<Question> getAllQuestions();
    public QuestionResponse getQuestionByParticularQuiz(Quiz quiz, Integer pageSize, Integer pageNumber);

}
