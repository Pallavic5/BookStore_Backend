package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.exception.OrderMessageException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.User;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.CartRepository;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.repository.UserRepository;
import com.example.bookstoreapp.util.EmailSenderService;
import com.example.bookstoreapp.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService{
    //Create dependency injection for OrderRepository class
    @Autowired
    OrderRepository orderRepo;
    //Create dependency injection for UserRepository class
    @Autowired
    UserRepository userRepo;
    //Create dependency injection for  BookRepository class
    @Autowired
    BookRepository bookRepo;
    //Create dependency injection for  CartRepository class
    @Autowired
    CartRepository cartRepo;
    //Create dependency injection for TokenUtil class
    @Autowired
    TokenUtil tokenUtil;
    //Create dependency injection for EmailSenderService class
    @Autowired
    EmailSenderService emailSenderService;

    //Apply logic for Insert order details in the database
    @Override
    public Order insert(OrderDTO orderDTO) throws Exception{
        Optional<User> user = userRepo.findById(orderDTO.getUser());
        List<Book> bookList=orderDTO.getBook().stream().map(book -> bookRepo.findById(book).get()).collect(Collectors.toList());
        long price=bookList.stream().mapToLong(n-> (n.getPrice())).sum();
        long quantity=bookList.stream().mapToLong(n-> n.getQuantity()).sum();
        if (user.isPresent()) {
            Order order = new Order(user.get(), bookList, price, quantity, orderDTO.getAddress(),orderDTO.getCancel());
            orderRepo.save(order);
            String token=tokenUtil.createToken(order.getOrderId());
            emailSenderService.sendEmail(user.get().getEmail(), "Order placed!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been placed successfully! Order details are below: \n\n Order id:  "+order.getOrderId()+"\n\n Order date:  "+order.getLocalDate()+"\n\n Order Price:  "+price+"\n Order quantity:  "+quantity+"\n Order address:  "+order.getAddress()+"\n Order user id:  "+order.getUser()+"\n Order book id:  "+bookList+"\n Order cancellation status:  "+order.isCancel());
            return order;
        } else {
            throw new OrderMessageException("User id or book id did not match! Please check and try again!");
        }
    }

    //Apply logic for fetching all order details present in the database
    @Override
    public List<Order> getAll(){
        List<Order> orderList=orderRepo.findAll();
        return orderList;
    }
    //Apply logic for fetching particular order details which will be found by id
    @Override
    public Order getById(long id){
        Optional<Order> order=orderRepo.findById(id);
        if(order.isPresent()){
            Order order1=orderRepo.findById(id).get();
            return order1;
        }else {
            throw new OrderMessageException("Sorry! We can not find order id: "+id);
        }
    }
    //Apply logic for Deleting particular order details which will be found by id
    @Override
    public String deleteById(long orderId, long userId){
        Optional<Order> order=orderRepo.findById(orderId);
        Optional<User> user = userRepo.findById(userId);
        if(order.isPresent() && user.isPresent()){
            orderRepo.deleteById(orderId);
            emailSenderService.sendEmail(user.get().getEmail(), "Order is deleted!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been deleted successfully! Order id: "+order.get().getOrderId());
            return "Details has been deleted!";
        }else {
            throw new OrderMessageException("Sorry! We can not find order id: "+orderId);
        }
    }
    //Apply logic for Updating particular order which will be found by id
    @Override
    public Order updateById(long id, OrderDTO orderDTO){
        Optional<User> user=userRepo.findById(orderDTO.getUser());
        List<Book> bookList=orderDTO.getBook().stream().map(book->bookRepo.findById(book).get()).collect(Collectors.toList());
        Order order=orderRepo.findById(id).get();
        if (orderRepo.findById(id).isPresent() && user.isPresent()){
            order.setAddress(orderDTO.getAddress());
            order.setUser(user.get());
            order.setBook(bookList.stream().map(book -> bookRepo.findById(book.getBookId()).get()).collect(Collectors.toList()));
            order.setCancel(orderDTO.getCancel());
          long price=bookList.stream().mapToLong(n->(n.getPrice())).sum();
            order.setPrice(price);
            long quantity=bookList.stream().mapToLong(n-> (n.getQuantity())).sum();
            order.setQuantity(quantity);
            orderRepo.save(order);
            String token=tokenUtil.createToken(order.getOrderId());
            emailSenderService.sendEmail(user.get().getEmail(), "Order is updated!","Hii...."+user.get().getFirstName()+" ! \n\n Your order has been updated successfully! Order details are below: \n\n Order id:  "+order.getOrderId()+"\n Order date:  "+ order.getLocalDate()+"\n Order Price:  "+price+"\n Order quantity:  "+quantity+"\n Order address:  "+order.getAddress()+"\n Order user id:  "+order.getUser()+"\n Order book id:  "+bookList+"\n Order cancellation status:  "+order.isCancel());
            return order;
        }else {
            throw new OrderMessageException("Order id, user id or book id did not match! Please check and try again!");
        }
    }
}
