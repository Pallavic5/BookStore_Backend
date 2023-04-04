package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BookDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}[A-Z]{1}[a-z0-9]{2,}$",message = "Book name should start with capital letter!")
    private String bookName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}\\s{0,}[A-Z]{1}[a-z]{2,}$",message = "Author name should start with capital letter!")
    private String authorName;
    @NotNull(message = "Book Description can not be null!")
    private String bookDescription;
    @NotEmpty(message = "Book image can not be empty!")
    private String bookImage;
    @NotNull(message = "Price can not be null!")
    private long price;
    @NotNull(message = "Quantity can not be null!")
    private long quantity;
}
