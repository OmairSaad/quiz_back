package com.exam.exam.Services.Impl;

import com.exam.exam.Exceptions.UserBadReq;
import com.exam.exam.Models.Question;
import com.exam.exam.Models.Quiz;
import com.exam.exam.Payloads.QuestionResponse;
import com.exam.exam.Repository.QuestionRepository;
import com.exam.exam.Services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question addQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public Question getQuestion(Long id) {
        return this.questionRepository.findById(id).orElseThrow(()-> new UserBadReq("Question not found"));
    }

    @Override
    public Question updateQuestion(Question question) {
        return this.questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = this.getQuestion(id);
        if(question.getQuiz() != null){
            question.getQuiz().getQuestions().remove(question);
        }
        this.questionRepository.delete(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return this.questionRepository.findAll();
    }

    @Override
    public QuestionResponse getQuestionByParticularQuiz(Quiz quiz, Integer pageSize, Integer pageNumber) {
        System.out.println(pageNumber + " "+ pageSize);
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Question> qn = questionRepository.findByquiz(quiz, pageable);
        QuestionResponse response = new QuestionResponse();

        response.setQuestions(qn.getContent());
        response.setLastPage(qn.isLast());
        response.setTotalpages(qn.getTotalPages());
        response.setPageNumber(qn.getNumber());
        response.setPageSize(qn.getSize());
//        Map<String, Object> response = new HashMap<>();
//        response.put("pageNumber", qn.getNumber());
//        response.put("pageSize", qn.getSize());
//        response.put("totalpages", qn.getTotalPages());
//        response.put("isLastPage", qn.isLast());
//        response.put("questions", qn.getContent());

        return response;
    }

}
