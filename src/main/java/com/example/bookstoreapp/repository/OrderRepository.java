package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//    @Transactional
//    @Modifying
   // @Query(value = "delete from practicedemo.book_order where book_order.order_id=:orderId",nativeQuery = true)
    void deleteById(Long orderId);
}
