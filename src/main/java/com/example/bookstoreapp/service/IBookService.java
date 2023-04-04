package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.model.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    Book insert(BookDTO bookDTO) throws Exception;

    List<Book> getAllBook();

    Optional<Book> getById(Long id);

    void deleteById(Long id);

    List<Book> searchByBookName(String bookName);

    Book updateBookById(BookDTO bookDTO, Long id);

    List<Book> sortingAscending();

    List<Book> sortingDescending();

    Book updateQuantity(BookDTO bookDTO, Long id);
}
