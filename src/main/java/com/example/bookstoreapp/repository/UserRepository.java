package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = "select * from practicedemo.user where user.email= :email",nativeQuery = true)
    User findByEmail(String email);
}
