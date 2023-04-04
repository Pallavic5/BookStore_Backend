package com.example.bookstoreapp.dto;

import lombok.Data;

@Data
public class ResponseUserDTO {
    private  String message;
    private Object data;

    public ResponseUserDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
