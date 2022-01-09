package ru.geekbrains.spring1.lesson8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring1.lesson8.entitites.Category;
import ru.geekbrains.spring1.lesson8.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring1.lesson8.exceptions.ShopError;
import ru.geekbrains.spring1.lesson8.repositories.CategoryRepository;

import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Page<Category> findAll(int pageIndex, int pageSize) {
        return categoryRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Category> findByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category update(Category category) {
        Category newCategory = categoryRepository.findById(category.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Категория с id=" + category.getId() + " не найдена"));
        newCategory.setTitle(category.getTitle());
        return categoryRepository.save(newCategory);
    }

    public ResponseEntity<?> deleteById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(new ShopError("Категория с id=" + id + " не найдена"), HttpStatus.NOT_FOUND);
        }
        categoryRepository.deleteById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }
}
