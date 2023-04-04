package com.example.bookstoreapp.dto;

import lombok.Data;

@Data
public class ResponseBookDTO {
    private  String message;
    private Object data;

    public ResponseBookDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
