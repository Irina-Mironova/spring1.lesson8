package ru.geekbrains.spring1.lesson8.dtos;

import lombok.Data;
import ru.geekbrains.spring1.lesson8.entitites.Product;

@Data
public class ProductDto {
    private Long id;
    private String title;
    private float price;
    private String category;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory().getTitle();
    }


}
