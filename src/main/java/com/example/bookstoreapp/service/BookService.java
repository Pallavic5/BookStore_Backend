package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.exception.BookMessageException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    //Create dependency injection for BookRepository class
    @Autowired
    BookRepository bookRepository;

    //Apply logic for insert book details in the database
    @Override
    public Book insert(BookDTO bookDTO){
        Book book=new Book(bookDTO);
        Long price = bookDTO.getQuantity()* bookDTO.getPrice();
        book.setPrice(price);
        bookRepository.save(book);
        return book;
    }
    //Apply logic for fetching all book details present in the database
    @Override
    public List<Book> getAllBook(){
        List<Book> bookList= bookRepository.findAll();
        return bookList;
    }
    //Apply logic for fetching particular book details which will be found by id
    @Override
    public Optional<Book> getById(Long id){
        Optional<Book> book= bookRepository.findById(id);
        if(book.isPresent()){
            return book;
        }else {
            throw new BookMessageException("Sorry! We can not find the book id: "+id);
        }
    }
    //Apply logic for Deleting particular book details which will be found by id
    @Override
    public void deleteById(Long id){
        Optional<Book> book= bookRepository.findById(id);
        if(book.isPresent()){
            bookRepository.deleteById(id);
        }else {
            throw new BookMessageException("Sorry! We can not find the book id: "+id);
        }
    }
    //Apply logic for Searching particular book details by its name
    @Override
    public List<Book> searchByBookName(String bookName){
        List<Book> book= bookRepository.findByName(bookName);
        if(bookRepository.findByName(bookName)==null){
            throw new BookMessageException("Sorry! We can not find your book name: "+bookName);
        }else {
            return book;
        }
    }
    //Apply logic for Updating particular book details which will be found by id
    @Override
    public Book updateBookById(BookDTO bookDTO,Long id){
        Book book= bookRepository.findById(id).get();
        if(bookRepository.findById(id).isPresent()){
            book.setBookName(bookDTO.getBookName());
            book.setAuthorName(bookDTO.getAuthorName());
            book.setBookDescription(book.getBookDescription());
            book.setBookImage(bookDTO.getBookImage());
            book.setPrice(bookDTO.getPrice());
            book.setQuantity(bookDTO.getQuantity());
            bookRepository.save(book);
            return book;
        }else {
            throw new BookMessageException("Sorry! We can not find entered id: "+id);
        }
    }
    //Apply logic for Sorting all book details by the price in ascending order
    @Override
    public List<Book> sortingAscending(){
        List<Book> bookList= bookRepository.sortingAscending();
        return bookList;
    }
    //Apply logic for Sorting all book details by the price in descending order
    @Override
    public List<Book> sortingDescending(){
        List<Book> bookList= bookRepository.sortingDescending();
        return bookList;
    }
    //Apply logic for Updating quantity for particular book which will be found by id
    @Override
    public Book updateQuantity(BookDTO bookDTO,Long id){
        Optional<Book> bookList= bookRepository.findById(id);
        if(bookList.isPresent()){
            Book book= bookRepository.findById(id).get();
            book.setQuantity(bookDTO.getQuantity());
            bookRepository.save(book);
            return book;
        }else {
            throw new BookMessageException("Sorry! We can not find entered id: "+id);
        }
    }
}
