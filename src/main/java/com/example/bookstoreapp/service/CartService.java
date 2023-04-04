package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.exception.CartMessageException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Cart;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.CartRepository;
import com.example.bookstoreapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;
    //Create dependency injection for UserRepo class
    @Autowired
    UserRepository userRepository;
    //Create dependency injection for bookRepo class
    @Autowired
    BookRepository bookRepository;
    //Apply logic for Insert cart details in the database
    @Override
    public Cart insert(CartDTO cartDTO){
        Optional<User> user = userRepository.findById(cartDTO.getUserId());
        Optional<Book> book = bookRepository.findById(cartDTO.getBookId());
        if(user.isPresent() && book.isPresent()){
            Cart cart=new Cart(user.get(),book.get(),cartDTO.getQuantity());
            cartRepository.save(cart);
            return cart;
        }else {
            throw new CartMessageException("User id or Book id is not present! Please provide the correct details!");
        }
    }
    //Apply logic for Getting all cart details present in the database
    @Override
    public List<Cart> getAll(){
        List<Cart> cartList = cartRepository.findAll();
        return cartList;
    }
    //Apply logic for fetching particular cart details which will be found by id
    @Override
    public Optional<Cart> getById(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()){
            return cart;
        }else {
            throw new CartMessageException("Sorry! We can not find the cart id: "+id);
        }
    }
    //Apply logic for fetching particular cart details which will be found by user id
    @Override
    public Optional<Cart> getByUserId(Long userId){
        Optional<Cart> cart = cartRepository.findByUserId(userId);
        if (cart.isPresent()){
            return cart;
        }else {
            throw new CartMessageException("Sorry! We can not find the cart user id: "+userId);
        }
    }
    //Apply logic for Deleting particular cart details which will be found by id
    @Override
    public void deleteById(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()){
            cartRepository.deleteById(id);
        }else {
            throw new CartMessageException("Sorry! We can not find cart id: "+id);
        }
    }
    //Apply logic for Updating particular cart details which will be found by id
    @Override
    public Cart updateById(CartDTO cartDTO,Long id){
        Optional<User> user=userRepository.findById(cartDTO.getUserId());
        Optional<Book> book=bookRepository.findById(cartDTO.getBookId());
        Cart cart=cartRepository.findById(id).get();
        if(cartRepository.findById(id).isPresent() && book.isPresent() && user.isPresent()){
            cart.setUserId(user.get());
            cart.setBookId(book.get());
            cart.setQuantity(cartDTO.getQuantity());
            cartRepository.save(cart);
            return cart;
        }else {
            throw new CartMessageException("Sorry! Your details are incorrect! Please check!");
        }
    }
    //Apply logic for Updating quantity for particular cart which will be found by id
    @Override
    public Cart UpdateQuantity(CartDTO cartDTO, Long id){
        Cart cart=cartRepository.findById(id).get();
        if(cartRepository.findById(id).isPresent()){
            cart.setQuantity(cartDTO.getQuantity());
            cartRepository.save(cart);
            return cart;
        }else {
            throw new CartMessageException("Sorry! We can not find cart id: "+id);
        }
    }


}
