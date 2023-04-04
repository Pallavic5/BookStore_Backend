package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.dto.ResponseOrderDTO;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")           //Global API for Order Controller
public class OrderController {
    //Create dependency injection using @Autowired for IOrderService class
    @Autowired
    IOrderService orderService;
    //Create API for insert order details in the database
    @PostMapping("/insert")
    public ResponseEntity<ResponseOrderDTO> insert(@Valid @RequestBody OrderDTO orderDTO) throws Exception {
        Order order = orderService.insert(orderDTO);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Order details are submitted!", order);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.CREATED);
    }
    //Create API for fetching all order details present in the database
    @GetMapping("/getAll")
    public ResponseEntity<ResponseOrderDTO> getAll() {
        List<Order> orderList = orderService.getAll();
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("All order details are found!", orderList);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.FOUND);
    }
    //Create API for fetching particular order details which will be found by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseOrderDTO> getById(@PathVariable Long id) {
        Order order = orderService.getById(id);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Searched order details by id is found!", order);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.FOUND);
    }
    //Create API for deleting particular order details which will be found by id
    @DeleteMapping("/delete/{orderId}/{userId}")
    public ResponseEntity<ResponseOrderDTO> deleteById(@PathVariable Long orderId, @PathVariable Long userId) {
        String result = orderService.deleteById(orderId, userId);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Cart details is deleted!", result);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.GONE);
    }
    //Create API for updating particular order details which will be found by id
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseOrderDTO> updateById(@Valid @PathVariable Long id, @RequestBody OrderDTO orderDTO) {
        Order order = orderService.updateById(id, orderDTO);
        ResponseOrderDTO responseOrderDTO = new ResponseOrderDTO("Searched details is updated!", order);
        return new ResponseEntity<>(responseOrderDTO, HttpStatus.ACCEPTED);
    }
}