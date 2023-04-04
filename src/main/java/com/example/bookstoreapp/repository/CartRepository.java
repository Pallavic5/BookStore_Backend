package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select * from practicedemo.cart where cart.cart_user_id=:userId",nativeQuery = true)
    Optional<Cart> findByUserId(Long userId);
//    @Transactional
//    @Modifying
   // @Query(value = "delete from bookstore.cart where cart.cart_id=:id",nativeQuery = true)
       void deleteById(Long id);
    @Query(value = "select cart_id from practicedemo.cart where cart.cart_user_id=:user",nativeQuery = true)
    Long findByCartUser(Long user);
}
