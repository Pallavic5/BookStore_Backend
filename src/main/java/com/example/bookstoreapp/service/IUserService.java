package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.LogInDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    String register(UserDTO userDTO) throws Exception;

    List<User> getAll();

    Optional<User> getByUserId(long id);

    User getByEmail(String email);

    User updateUserByEmail(UserDTO userDTO, String email);

    User forgotPassword(UserDTO userDTO, String email);

    void deleteById(long id, String token);

    User getByToken(String token);

    String login(LogInDTO loginDTO);

    User changePassword(LogInDTO loginDTO, String token, String email);
}
