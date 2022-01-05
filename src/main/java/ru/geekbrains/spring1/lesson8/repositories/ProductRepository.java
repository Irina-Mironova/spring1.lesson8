package ru.geekbrains.spring1.lesson8.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring1.lesson8.entitites.Product;


@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

}
