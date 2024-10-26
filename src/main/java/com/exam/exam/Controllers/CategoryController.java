package com.exam.exam.Controllers;

import com.exam.exam.Models.Category;
import com.exam.exam.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
    @PostMapping("/")
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return new ResponseEntity<>(categoryService.addCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("/{cid}")
    public ResponseEntity<?> getCategory(@PathVariable Long cid) {
        return ResponseEntity.ok(categoryService.getCategory(cid));
    }

    @DeleteMapping("/{cid}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long cid) {
        categoryService.deleteCategory(cid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        return  new ResponseEntity<>(categoryService.updateCategory(category), HttpStatus.OK);
    }
}
