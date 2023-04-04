package com.example.bookstoreapp.dto;

import lombok.Data;

@Data
public class ResponseOrderDTO {
    private  String message;
    private Object data;

    public ResponseOrderDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
