package com.exam.exam.Controllers;

import com.exam.exam.Models.Category;
import com.exam.exam.Models.Quiz;
import com.exam.exam.Services.CategoryService;
import com.exam.exam.Services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz")
@CrossOrigin
public class QuizController {
    @Autowired
    private QuizService quizService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<?> getAllQuiz(){
        return ResponseEntity.ok(quizService.getAllQuiz());
    }

    @GetMapping("/{quizId}")
    public ResponseEntity<?> getQuizById(@PathVariable Long quizId){
        return ResponseEntity.ok(this.quizService.getQuiz(quizId));
    }
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        return new ResponseEntity<>(this.quizService.addQuiz(quiz), HttpStatus.CREATED);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<?> deleteQuiz(@PathVariable Long quizId){
          this.quizService.deleteQuiz(quizId);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Update Quiz, passing with quizId so it updated
    @PutMapping("/")
    public ResponseEntity<?> updateQuiz(@RequestBody Quiz quiz){
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //getAllActiveQuizzes
    @GetMapping("/active")
    public ResponseEntity<?> getActiveQuiz(){
        return ResponseEntity.ok(this.quizService.getAllActiveQuizzes());
    }

    //getAllQuizOfPerticullar Category
    @GetMapping("/category/{catId}")
    public ResponseEntity<?> getCategoryQuizzes(@PathVariable Long catId){
        Category category = this.categoryService.getCategory(catId);
        return ResponseEntity.ok(this.quizService.getAllActiveQuizzesByCategor(category));
    }
}
