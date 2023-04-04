package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService{
    Cart insert(CartDTO cartDTO) throws Exception;
    List<Cart> getAll();

    Optional<Cart> getById(Long id);
    Optional<Cart> getByUserId(Long userId);

    void deleteById(Long id);

    Cart updateById(CartDTO cartDTO, Long id);

    Cart UpdateQuantity(CartDTO cartDTO, Long id);

}
