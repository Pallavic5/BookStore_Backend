package com.example.bookstoreapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class BookStoreProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookStoreProjectApplication.class, args);
        System.out.println("<-----------------------Application Running Successfully------------------------->");
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("<-----------------------Welcome To BookStore Application------------------------->");
        System.out.println("----------------------------------------------------------------------------");
        log.info("!!!.............Hello Logger...........!!!");    }
}
