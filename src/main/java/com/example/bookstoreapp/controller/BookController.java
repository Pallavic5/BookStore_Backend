package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.dto.ResponseBookDTO;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")        //This is a global API for book Controller
public class BookController {
    //Create dependency injection using @Autowired for IBookService class
    @Autowired
    IBookService bookService;
    //Create API for insert book details in the database
    @PostMapping("/insert")
    public ResponseEntity<ResponseBookDTO> insert(@Valid @RequestBody BookDTO bookDTO) throws Exception {
        Book book = bookService.insert(bookDTO);
        ResponseBookDTO responseDTO = new ResponseBookDTO("Book details has been submitted!", book);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
    //Create API for fetching all book details present in the database
    @GetMapping("/getAllDetails")
    public ResponseEntity<ResponseBookDTO> getAllBook() {
        List<Book> bookList = bookService.getAllBook();
        ResponseBookDTO responseDTO = new ResponseBookDTO("All books details are found!", bookList);
        return new ResponseEntity<>(responseDTO, HttpStatus.FOUND);
    }
    //Create API for fetching particular book details which will be found by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBookDTO> getById(@PathVariable Long id) {
        Optional<Book> book = bookService.getById(id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Searched book by id is found!", book);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.FOUND);
    }
    //Create API for Deleting particular book details which will be found by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseBookDTO> deleteById(@PathVariable Long id) {
        bookService.deleteById(id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Book details are deleted Successfully!", "Deleted book id is: " + id);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.GONE);
    }
    //Create API for Searching particular book details by its name
    @GetMapping("/searchByBookName/{name}")
    public ResponseEntity<ResponseBookDTO> searchByBookName(@PathVariable String name) {
        List<Book> book = bookService.searchByBookName(name);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Your Book is found....!!", book);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.FOUND);
    }
    //Create API for Updating particular book details which will be found by id
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookById(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) {
        Book book = bookService.updateBookById(bookDTO, id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Your book details is updated!", book);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.ACCEPTED);
    }
    //Create API for Sorting all book details by the price in ascending order
    @GetMapping("/sortingAscending")
    public ResponseEntity<ResponseBookDTO> sortingAscending() {
        List<Book> bookList = bookService.sortingAscending();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("All book details are showing in ascending order!", bookList);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    //Create API for Sorting all book details by the price in descending order
    @GetMapping("/sortingDescending")
    public ResponseEntity<ResponseBookDTO> sortingDescending() {
        List<Book> bookList = bookService.sortingDescending();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("All book details are showing in ascending order!", bookList);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
    }
    //Create API for Updating quantity for particular book which will be found by id
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseBookDTO> updateQuantity(@Valid @RequestBody BookDTO bookDTO, @PathVariable Long id) {
        Book book = bookService.updateQuantity(bookDTO, id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Your book details is updated!", book);
        return new ResponseEntity<>(responseBookDTO, HttpStatus.ACCEPTED);
    }
}