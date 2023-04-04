package com.example.bookstoreapp.service;
import com.example.bookstoreapp.dto.LogInDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.exception.UserMessageException;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.util.EmailSenderService;
import com.example.bookstoreapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    //Create dependency injection for UserRepository class
    @Autowired
    UserRepository userRepository;
    //Create dependency injection for TokenUtil class
    @Autowired
    TokenUtil tokenUtil;
    //Create dependency injection for EmailSenderService class
    @Autowired
    EmailSenderService emailSenderService;

    //Apply logic for Insert user details in the database
    @Override
    public String register(UserDTO userDTO){
        User user=new User(userDTO);
        userRepository.save(user);
        String token=tokenUtil.createToken(user.getUserId());
        emailSenderService.sendEmail(user.getEmail(),"Registered in Book Store Application", "Hii...."+user.getFirstName()+"\n You have been successfully registered!\n\n Your registered details are:\n\n User Id:  "+user.getUserId()+"\n First Name:  "+user.getFirstName()+"\n Last Name:  "+user.getLastName()+"\n Email:  "+user.getEmail()+"\n Address:  "+user.getAddress()+"\n DOB:  "+user.getDob() +"\n Token:  "+ token);
        return token;
    }
    //Apply logic for Getting all user details present in the database
    @Override
    public List<User> getAll(){
        List<User> list=userRepository.findAll();
        return list;
    }
    //Apply logic for Getting particular user details which will be found by id
    @Override
    public Optional<User> getByUserId(long id){
        Optional<User> user=userRepository.findById(id);
        if (user.isPresent()){
            return user;
        }else {
            throw new UserMessageException("Sorry! We cannot find the user id: "+id);
        }
    }
    //Apply logic for Getting particular user details which will be found by email
    @Override
    public User getByEmail(String email){
        User userList=userRepository.findByEmail(email);
        if(userList==null){
            throw new UserMessageException("Sorry! We can not find the user email: "+email);
        }else {
            return userList;
        }
    }
    //Apply logic for updating password using id
    @Override
    public User forgotPassword(UserDTO userDTO, String email){
        User user=userRepository.findByEmail(email);
        if (user!=null){
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            emailSenderService.sendEmail(user.getEmail(),"Password Updated", "Hii...."+user.getFirstName()+" Your Password has been updated!\n\n Your New password: "+user.getPassword());
            return user;
        }else {
            throw new UserMessageException("Sorry! We can not find the user email: "+email);
        }
    }
    //Create Api for Updating user details by email id
    @Override
    public User updateUserByEmail(UserDTO userDTO, String email){
        User user=userRepository.findByEmail(email);
        if(userRepository.findByEmail(email)!=null) {
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());
            user.setDob(userDTO.getDob());
            user.setPassword(userDTO.getPassword());
            userRepository.save(user);
            emailSenderService.sendEmail(user.getEmail(), "Your details are updated!", "Hii...." + user.getFirstName() + " Your updated details are:\n\n First Name:  " + user.getFirstName() + "\n Last Name:  " + user.getLastName() + "\n Email:  " + user.getEmail() + "\n Address:  " + user.getAddress() + "\n DOB:  " + user.getDob());
            return user;
        }else {
            throw new UserMessageException("Sorry! We can not find entered email: "+email);
        }
    }
    //Apply logic for Check user is logged in with the correct email and password or not
    @Override
    public String login(LogInDTO loginDTO){
        Optional<User> user= Optional.ofNullable(userRepository.findByEmail(loginDTO.getEmail()));
        if (user.isPresent() && user.get().getPassword().equals(loginDTO.getPassword()) ){
            emailSenderService.sendEmail(user.get().getEmail(),"Logged in Successfully!", "Hii...."+user.get().getFirstName()+"\n\n You have successfully logged in into Book Store App!");
            return "You have logged in successfully!";
        }else {
            throw new UserMessageException("Sorry! Email or Password is incorrect!");
        }
    }
    //Apply logic for delete particular details by using id
    @Override
    public void deleteById(long id, String token){
        long userId=tokenUtil.decodeToken(token);
        Optional<User> user=userRepository.findById(id);
        Optional<User> userToken=userRepository.findById(userId);
        if(user.get().getFirstName().equals(userToken.get().getFirstName())){
            userRepository.deleteById(id);
            emailSenderService.sendEmail(user.get().getEmail(), "Order is deleted!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been deleted successfully! Order id: "+id);
        }else {
            throw new UserMessageException("Sorry! We can not find the user id: "+id);
        }
    }
    //Apply logic for get particular details by using token
    @Override
    public User getByToken(String token){
        Long userId=tokenUtil.decodeToken(token);
        Optional<User> user=userRepository.findById(userId);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new UserMessageException("User is not found!");
        }
    }
    //Apply logic for changepassword by using mail and token.
    @Override
    public User changePassword(LogInDTO loginDTO, String email, String token) {
        Long userID = tokenUtil.decodeToken(token);
        Optional<User> user = userRepository.findById(userID);
        User user2 = userRepository.findByEmail(email);
        if(user.get().getEmail().equals(user2.getEmail()) && user.get().getPassword().equals(user2.getPassword()))
        {
            user2.setPassword(loginDTO.getPassword());
            userRepository.save(user2);
            return user2;
        }else{
            throw new UserMessageException("User is not found!");
        }
    }

}



