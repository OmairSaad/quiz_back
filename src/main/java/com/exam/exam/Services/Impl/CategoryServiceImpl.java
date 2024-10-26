package com.exam.exam.Services.Impl;

import com.exam.exam.Exceptions.UserBadReq;
import com.exam.exam.Models.Category;
import com.exam.exam.Repository.CategRepository;
import com.exam.exam.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategRepository categRepository;
    @Override
    public Category addCategory(Category category) {
        return this.categRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categRepository.save(category);
    }

    @Override
    public Category getCategory(Long id) {
        return this.categRepository.findById(id).orElseThrow(() -> new UserBadReq("Category not found"));
    }

    @Override
    public void deleteCategory(Long id) {
       Category cg= categRepository.findById(id).orElseThrow(() -> new UserBadReq("Category not found"));
        this.categRepository.delete(cg);
    }

    @Override
    public List<Category> getAllCategories() {
        return this.categRepository.findAll();
    }
}
