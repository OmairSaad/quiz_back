package com.exam.exam.Controllers;

import com.exam.exam.Models.Question;
import com.exam.exam.Models.Quiz;
import com.exam.exam.Services.QuestionService;
import com.exam.exam.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/question")
@CrossOrigin
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //Ad Question
    @GetMapping("/")
    public ResponseEntity<?> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("{quesId}")
    public ResponseEntity<?> getQuestion(@PathVariable Long quesId) {
        return ResponseEntity.ok(questionService.getQuestion(quesId));
    }
    @PostMapping("/")
    public ResponseEntity<?> addQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.addQuestion(question));
    }

    //This will give all the questions with pagination.
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionByQuizAdmin(@PathVariable Long quizId, @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize ){
        Quiz q = quizService.getQuiz(quizId);
        return  ResponseEntity.ok(questionService.getQuestionByParticularQuiz(q, pageSize, pageNumber));
    }

    //This will give only numbers of questions that are given in quizz
    @GetMapping("/quiz/specific/{quizId}")
    public ResponseEntity<?> getQuesionByQuizUser(@PathVariable Long quizId){
        Quiz qz = quizService.getQuiz(quizId);
        List<Question> questions = qz.getQuestions();
        if(questions.size()> Integer.parseInt(qz.getNumberOfQuestions())){
             questions = questions.subList(0,Integer.parseInt(qz.getNumberOfQuestions()));
        }
        Collections.shuffle(questions);     //for random
        questions.forEach(question->{
            question.setAnswer("");   //hide answer on user console
        });
        return ResponseEntity.ok(questions);
    }


    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@RequestBody  Question question) {
        return ResponseEntity.ok(questionService.updateQuestion(question));
    }


    @DeleteMapping("/{quesId}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long quesId) {
        questionService.deleteQuestion(quesId);
        return ResponseEntity.ok().build();
    }


    //Evaluate the correct answers
    @PostMapping("/evaluate/")
    public ResponseEntity<?> Evaluate(@RequestBody List<Question> questions) {
        AtomicInteger correctanswer= new AtomicInteger();
        AtomicInteger attempt = new AtomicInteger();
        questions.forEach(question -> {
            //db question has actual answer, in question list we set answer ="" how to verify
            Question dbQuestion = this.questionService.getQuestion(question.getQuesId());
            if(dbQuestion.getAnswer().equals(question.getGivenanswer())){
                correctanswer.getAndIncrement();
            }
            if(question.getGivenanswer() != null){//in string null not
                attempt.getAndIncrement();
            }

        });
        Map<String, Object> res = Map.of("correctAnswer", correctanswer.get(),"incorrectAnswer", attempt.get()- correctanswer.get(),"attemptQuestion", attempt.get(), "date", LocalDateTime.now());
        return  ResponseEntity.ok(res);
    }
}
