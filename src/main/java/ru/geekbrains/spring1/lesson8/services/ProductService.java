package ru.geekbrains.spring1.lesson8.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring1.lesson8.entitites.Product;
import ru.geekbrains.spring1.lesson8.exceptions.ShopError;
import ru.geekbrains.spring1.lesson8.repositories.ProductRepository;

import java.util.Optional;


@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(int pageIndex, int pageSize) {
        return productRepository.findAll(PageRequest.of(pageIndex, pageSize));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public ResponseEntity<?> deleteById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(new ShopError("Продукт с id=" + id + " не найден"), HttpStatus.NOT_FOUND);
        }
        productRepository.deleteById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
