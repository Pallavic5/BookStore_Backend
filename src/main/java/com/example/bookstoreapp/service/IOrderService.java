package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.model.Order;

import java.util.List;

public interface IOrderService {
    Order insert(OrderDTO orderDTO) throws Exception;

    List<Order> getAll();

    Order getById(long id);


    Order updateById(long id, OrderDTO orderDTO) ;

    String deleteById(long orderId, long userId);
}
