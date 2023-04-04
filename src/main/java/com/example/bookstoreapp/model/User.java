package com.example.bookstoreapp.model;

import com.example.bookstoreapp.dto.LogInDTO;
import com.example.bookstoreapp.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
public class User {
    //User table attribute
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;

    public User(UserDTO userDTO){
        this.firstName=userDTO.getFirstName();
        this.lastName=userDTO.getLastName();
        this.email=userDTO.getEmail();
        this.address=userDTO.getAddress();
        this.dob=userDTO.getDob();
        this.password=userDTO.getPassword();
    }

    public User(LogInDTO loginDTO) {
        this.email=loginDTO.getEmail();
        this.password=loginDTO.getPassword();
    }
}
