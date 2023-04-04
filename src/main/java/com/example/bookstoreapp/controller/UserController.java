package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.LogInDTO;
import com.example.bookstoreapp.dto.ResponseUserDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
RestController is a Spring annotation that is used to build REST API in a declarative way.
RestController annotation is applied to a class to mark it as a request handler, and Spring
will do the building and provide the RESTful web service at runtime.
 */

@CrossOrigin
@RestController
@RequestMapping("/user")            //This API is global
public class UserController {
    //Create dependency injection using @Autowired for IUserService class
    @Autowired
    IUserService userService;
    //Create API for insert details into the database
    @PostMapping("/register")
    public ResponseEntity<ResponseUserDTO> register(@Valid @RequestBody UserDTO userDTO) throws Exception {
        String user=userService.register(userDTO);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("User details are submitted!",user);
        return new ResponseEntity<>(responseUserDTO, HttpStatus.CREATED);
    }
    //Create API for fetching all present user details from the database
    @GetMapping("/getAll")
    public ResponseEntity<ResponseUserDTO> getAll(){
        List<User> user=userService.getAll();
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("All user details are found!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    //Create API for fetching particular user details which will be found by id
    @GetMapping("/getByUserId/{id}")
    public ResponseEntity<ResponseUserDTO> getByUserId(@PathVariable Long id){
        Optional<User> user=userService.getByUserId(id);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("User details by id is found!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    //Create API for fetching particular user details which will be found by email
    @GetMapping("/getByEmail/{email}")
    public ResponseEntity<ResponseUserDTO> getByEmail(@PathVariable String email){
        User users=userService.getByEmail(email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Searched user by email id is found!",users);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.FOUND);
    }
    //Create API for Changing or updating password using email
    @PutMapping("/forgotPassword/{email}")
    public ResponseEntity<ResponseUserDTO> forgotPassword(@Valid @RequestBody UserDTO userDTO, @PathVariable String email){
        User user=userService.forgotPassword(userDTO,email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("Password has been changed successfully!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.ACCEPTED);
    }
    // Ability to call API to change password
    @PutMapping("/changepassword/{email}/{token}")
    public ResponseEntity<ResponseUserDTO> changePassword(@RequestBody LogInDTO loginDTO, @PathVariable String email,@PathVariable String token){
        User user = userService.changePassword(loginDTO,email,token);
        ResponseUserDTO responseUserDTO = new ResponseUserDTO("Password has been reset successfully!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    //Create Api for Updating user details by email id
    @PutMapping("/updateUserByEmail/{email}")
    public ResponseEntity<ResponseUserDTO> updateUserByEmail(@RequestBody UserDTO userDTO, @PathVariable String email){
        User user=userService.updateUserByEmail(userDTO,email);
        ResponseUserDTO responseUserDTO =new ResponseUserDTO("User details has been updated successfully!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    //Create API for Check whether user is logged in with the correct email and password or not
    @PostMapping("/login")
    public ResponseEntity<ResponseUserDTO> login(@Valid @RequestBody LogInDTO loginDTO){
        String result=userService.login(loginDTO);
        ResponseUserDTO responseUserDTO=new ResponseUserDTO("You have successfully log in!",result);
        return  new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
    //Create API for Delete particular user details by id and token
    @DeleteMapping("/delete/{id}/{token}")
    public ResponseEntity<ResponseUserDTO> deleteById(@PathVariable Long id, @PathVariable String token){
        userService.deleteById(id,token);
        ResponseUserDTO responseUserDTO=new ResponseUserDTO("User details has been deleted!","Deleted user id is: "+id);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.GONE);
    }
    //Create API for fetching user details by token
    @GetMapping("/getByToken/{token}")
    public ResponseEntity<ResponseUserDTO> getByToken(@PathVariable String token){
        User user=userService.getByToken(token);
        ResponseUserDTO responseUserDTO=new ResponseUserDTO("User found!",user);
        return new ResponseEntity<>(responseUserDTO,HttpStatus.OK);
    }
}