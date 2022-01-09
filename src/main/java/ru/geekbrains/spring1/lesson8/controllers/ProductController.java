package ru.geekbrains.spring1.lesson8.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring1.lesson8.dtos.ProductDto;
import ru.geekbrains.spring1.lesson8.entitites.Category;
import ru.geekbrains.spring1.lesson8.entitites.Product;
import ru.geekbrains.spring1.lesson8.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring1.lesson8.services.CategoryService;
import ru.geekbrains.spring1.lesson8.services.ProductService;


@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;
    private CategoryService categoryService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(defaultValue = "1") int pageIndex) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        Page<ProductDto> productPage = productService.findAll(pageIndex - 1, 5).map(ProductDto::new);
        return productPage;
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return new ProductDto(productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Продукт id = " + id + " не найден")));
    }


    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategory()).orElseThrow(() ->
                new ResourceNotFoundException("Категория с названием= " + productDto.getCategory() + " не найдена"));
        product.setCategory(category);
        productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = productService.findById(productDto.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Продукт с id = " + productDto.getId() + " не найден"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        Category category = categoryService.findByTitle(productDto.getCategory()).orElseThrow(() ->
                new ResourceNotFoundException("Категория с названием= " + productDto.getCategory() + " не найдена"));
        product.setCategory(category);
        productService.save(product);
        return productDto;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return productService.deleteById(id);
    }
}
