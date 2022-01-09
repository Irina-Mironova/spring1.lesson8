package ru.geekbrains.spring1.lesson8.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
public class ShopError {
    private String message;
    Date date;

    public ShopError(String message) {
        this.message = message;
        this.date = new Date();
    }
}
