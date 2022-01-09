package ru.geekbrains.spring1.lesson8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring1.lesson8.entitites.Category;
import ru.geekbrains.spring1.lesson8.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring1.lesson8.services.CategoryService;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<Category> findAll(@RequestParam(defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        return categoryService.findAll(pageIndex - 1, 5);
    }

    @GetMapping("/{id}")
    public Category findById(@PathVariable Long id) {
        return categoryService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Категория с id= " + id + " не найдена"));
    }

    @PostMapping
    public Category save(@RequestBody Category category) {
        Category newCategory = new Category();
        newCategory.setTitle(category.getTitle());
        return categoryService.save(newCategory);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Category update(@RequestBody Category category) {
        return categoryService.update(category);
    }


    // Ошибка в этом месте при удалении категории: Нарушение ссылочной целостности.
    // Подскажите, пож-та, на след. уроке, как корректнее будет организовывать удаление категорий,
    // если существуют еще и продукты в этой категории (либо, может быть, вообще запретить это действие?)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return categoryService.deleteById(id);
    }
}
