package com.example.bookstoreapp.model;

import com.example.bookstoreapp.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book {
    //Book table attributes
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImage;
    private long price;
    private long quantity;

    //Create Constructor
    public Book(BookDTO bookDTO) {
        this.bookName = bookDTO.getBookName();
        this.authorName = bookDTO.getAuthorName();
        this.bookDescription = bookDTO.getBookDescription();
        this.bookImage = bookDTO.getBookImage();
        this.price = bookDTO.getPrice();
        this.quantity = bookDTO.getQuantity();
    }
}