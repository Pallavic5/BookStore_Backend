package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseCartDTO;
import com.example.bookstoreapp.model.Cart;
import com.example.bookstoreapp.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart")        //This is the global API for cart controller
public class CartController {
    //Create dependency injection using @Autowired for ICartService class
    @Autowired
    ICartService cartService;

    //Create API for Insert cart details in the database
    @PostMapping("/insert")
    public ResponseEntity<ResponseCartDTO> insert(@Valid @RequestBody CartDTO cartDTO) throws Exception{
        Cart cart=cartService.insert(cartDTO);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart details are added!",cart);
        return new ResponseEntity<>(responseCartDTO, HttpStatus.CREATED);
    }
    //Create API for fetching all cart details present in the database
    @GetMapping("/getAll")
    public ResponseEntity<ResponseCartDTO> getAll(){
        List<Cart> cartList=cartService.getAll();
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("All cart details are found!",cartList);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    //Create API for fetching particular cart details which will be found by id
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseCartDTO> getById(@PathVariable Long id){
        Optional<Cart> cart=cartService.getById(id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Searched cart details by id is found!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    //Create API for fetching particular cart details which will be found by user id
    @GetMapping("/getByUserId/{userId}")
    public ResponseEntity<ResponseCartDTO> getByUserId(@PathVariable Long userId){
        Optional<Cart> cart=cartService.getByUserId(userId);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Searched cart details by id is found!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.FOUND);
    }
    //Create API for Deleting particular cart details which will be found by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseCartDTO> deleteById(@PathVariable Long id){
        cartService.deleteById(id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Cart details is deleted!","Deleted cart id is: "+id);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.GONE);
    }
    //Create API for Updating particular cart details which will be found by id
    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseCartDTO> updateById(@Valid @RequestBody CartDTO cartDTO,@PathVariable Long id){
        Cart cart=cartService.updateById(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart details is updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
    }
    //Create API for Updating quantity for particular cart which will be found by id
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseCartDTO> updateQuantity(@Valid @RequestBody CartDTO cartDTO,@PathVariable Long id){
        Cart cart=cartService.UpdateQuantity(cartDTO,id);
        ResponseCartDTO responseCartDTO=new ResponseCartDTO("Your cart quantity is updated!",cart);
        return new ResponseEntity<>(responseCartDTO,HttpStatus.ACCEPTED);
    }
}
