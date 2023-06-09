package com.example.bookstoreapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserDTO {
     @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$",message = "First name should start with capital letter")
    private String firstName;
     @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$",message = "Last name should start with capital letter")
    private String lastName;
      @Email(message = "Email should be in email format!")
    private String email;
      @NotNull(message = "Address can not be null!")
    private String address;
      @Past(message = "DOB should be past date!")
    private LocalDate dob;
     @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&*()-+=])(?=.*[0-9])([a-zA-Z0-9@._-]).{8,}$",message = "Password should contain less than 8 characters")
    private String password;
}
