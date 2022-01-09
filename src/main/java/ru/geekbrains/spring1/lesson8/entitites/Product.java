package ru.geekbrains.spring1.lesson8.entitites;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;


    @Column(name = "price")
    private float price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
