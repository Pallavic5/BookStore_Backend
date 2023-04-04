package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
    @Query(value = "select * from practicedemo.book where book.book_name= :bookName",nativeQuery = true)
    List<Book> findByName(String bookName);
    @Query(value = "select * from practicedemo.book order by price ASC",nativeQuery = true)
    List<Book> sortingAscending();
    @Query(value = "select * from practicedemo.book order by price DESC",nativeQuery = true)
    List<Book> sortingDescending();
}
